package com.jardim.dto.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jardim.dto.BeneficiarioDTO;
import com.jardim.dto.BeneficiarioRequestDTO;
import com.jardim.dto.DocumentoDTO;
import com.jardim.model.Beneficiario;
import com.jardim.model.Documento;


/**
 * Classe mapear Beneficiario entity para BeneficiarioDTO e vice-versa.
 */
@Component
public class BeneficiarioMapper {

    public Beneficiario toModel(BeneficiarioRequestDTO beneficiarioRequestDTO) {

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome(beneficiarioRequestDTO.nome());
        beneficiario.setTelefone(beneficiarioRequestDTO.telefone());
        beneficiario.setDataNascimento(beneficiarioRequestDTO.dataNascimento());
        
        Set<Documento> documentos = beneficiarioRequestDTO.documentos().stream()
                .map(documentoDTO -> {
                	Documento documento = new Documento();
                    if (documento.getId() != null && documento.getId() > 0) {
                    	documento.setId(documentoDTO._id());
                    }
                    documento.setTipoDocumento(documentoDTO.tipoDocumento());
                    documento.setDescricao(documentoDTO.descricao());
                    documento.setBeneficiario(beneficiario);
                    documento.setDataInclusao(new Date());
                    return documento;
                }).collect(Collectors.toSet());
        beneficiario.setDocumentos(documentos);

        return beneficiario;
    }

    public BeneficiarioDTO toDTO(Beneficiario beneficiario) {
        if (beneficiario == null) {
            return null;
        }
        List<DocumentoDTO> documentoDTOList = beneficiario.getDocumentos()
                .stream()
                .map(documento -> new DocumentoDTO(documento.getId(), documento.getTipoDocumento(), documento.getDescricao()))
                .toList();
        return new BeneficiarioDTO(beneficiario.getId(), beneficiario.getNome(), beneficiario.getTelefone(), beneficiario.getDataNascimento(), beneficiario.getDataInclusao(), beneficiario.getDataAtualizacao(), documentoDTOList);
    }
    
    public Documento convertDocumentoDTOToDocumento(DocumentoDTO documentoDTO) {
    	Documento documento = new Documento();
    	documento.setId(documentoDTO._id());
    	documento.setTipoDocumento(documentoDTO.tipoDocumento());
    	documento.setDescricao(documentoDTO.descricao());
    	documento.setDataInclusao(new Date());
        return documento;
    }
    

}
