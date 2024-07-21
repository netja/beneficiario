package com.jardim.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BeneficiarioRequestDTO(
        @NotBlank @NotNull @Length(min = 5, max = 50) String nome,
        @NotBlank @NotNull @Length(min = 8, max = 20) String telefone,
        @NotNull Date dataNascimento,
        List<DocumentoDTO> documentos
        ) {
}
