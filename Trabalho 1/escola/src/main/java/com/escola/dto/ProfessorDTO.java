package com.escola.dto;

import jakarta.validation.constraints.NotBlank;
public record ProfessorDTO (
        @NotBlank String nome,
        @NotBlank String email
        ) {

}
