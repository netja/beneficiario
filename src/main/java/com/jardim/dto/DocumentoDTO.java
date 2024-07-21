package com.jardim.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DocumentoDTO (
	Long _id,
    @NotBlank @NotNull @Length(min = 5, max = 100) String tipoDocumento,
    @NotBlank @NotNull @Length(min = 5, max = 100) String descricao) {
}
