package com.jardim.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BeneficiarioDTO(
        @JsonProperty("_id") Long id,
        String nome, String telefone, Date dataNascimento, Date dataInclusao, Date dataAtualizacao, List<DocumentoDTO> documentos) {
}
