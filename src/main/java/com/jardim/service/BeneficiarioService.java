package com.jardim.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.jardim.dto.BeneficiarioDTO;
import com.jardim.dto.BeneficiarioRequestDTO;
import com.jardim.dto.mapper.BeneficiarioMapper;
import com.jardim.exception.RecordNotFoundException;
import com.jardim.exception.ValidException;
import com.jardim.model.Beneficiario;
import com.jardim.model.Documento;
import com.jardim.repository.BeneficiarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
@SuppressWarnings("null")
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final BeneficiarioMapper beneficiarioMapper;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, BeneficiarioMapper beneficiarioMapper) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.beneficiarioMapper = beneficiarioMapper;
    }
    
    public  List<BeneficiarioDTO> findAll() {
    	return beneficiarioRepository.findAll().stream().map(beneficiarioMapper::toDTO).toList();
    }

    public List<BeneficiarioDTO> findByNome(@NotNull @NotBlank String nome) {
        return beneficiarioRepository.findByNome(nome).stream().map(beneficiarioMapper::toDTO).toList();
    }

    public BeneficiarioDTO findById(@Positive @NotNull Long id) {
        return beneficiarioRepository.findById(id).map(beneficiarioMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public BeneficiarioDTO create(@Valid BeneficiarioRequestDTO beneficiarioRequestDTO) {
       beneficiarioRepository.findByNome(beneficiarioRequestDTO.nome()).stream()
                .findAny().ifPresent(c -> {
                    throw new  ValidException("Um beneficiario com nome " + beneficiarioRequestDTO.nome() + " já existe.");
                });
        Beneficiario beneficiario = beneficiarioMapper.toModel(beneficiarioRequestDTO);
        beneficiario.setDataInclusao(new Date());
        return beneficiarioMapper.toDTO(beneficiarioRepository.save(beneficiario));
    }

    public BeneficiarioDTO update(@Positive @NotNull Long id, @Valid BeneficiarioRequestDTO beneficiarioRequestDTO) {
        return beneficiarioRepository.findById(id).map(actual -> {
            actual.setNome(beneficiarioRequestDTO.nome());
            actual.setTelefone(beneficiarioRequestDTO.telefone());
            actual.setDataNascimento(beneficiarioRequestDTO.dataNascimento());
            actual.setDataAtualizacao(new Date());
            mergeDocumentosForUpdate(actual, beneficiarioRequestDTO);
            return beneficiarioMapper.toDTO(beneficiarioRepository.save(actual));
        })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    private void mergeDocumentosForUpdate(Beneficiario updatedBeneficiario, BeneficiarioRequestDTO beneficiarioRequestDTO) {
        // busca documentos para remover    	
    	List<Documento> documentoToRemove = updatedBeneficiario.getDocumentos().stream()
                .filter(documento -> beneficiarioRequestDTO.documentos().stream()
                        .anyMatch(documentoDto -> documentoDto._id() != null && documentoDto._id() != 0 && documentoDto._id() != documento.getId()))
                .toList();
        documentoToRemove.forEach(updatedBeneficiario::removeDocumento);

        beneficiarioRequestDTO.documentos().forEach(documentoDto -> {
            // novo documento, add 
            if ( documentoDto._id() == null  || documentoDto._id() == 0) {
                updatedBeneficiario.addDocumento(beneficiarioMapper.convertDocumentoDTOToDocumento(documentoDto));
            } else {
            	// documento existe, busca e atualiza
                updatedBeneficiario.getDocumentos().stream()
                        .filter(documento -> documento.getId() == documentoDto._id())
                        .findAny()
                        .ifPresent(documento -> {
                        	documento.setTipoDocumento(documentoDto.tipoDocumento());
                        	documento.setDescricao(documentoDto.descricao());
                        	documento.setDataAtualizacao(new Date());
                        });
            }
        }); 
    }

    public void delete(@Positive @NotNull Long id) {
        beneficiarioRepository.delete(beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
