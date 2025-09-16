package com.escola.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CursoDTO(
        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        String descricao,

        @NotNull(message = "A data de início é obrigatória.")
        LocalDate dataInicio,

        @NotNull(message = "A data final é obrigatória.")
        @Future(message = "A data final deve ser no futuro.")
        LocalDate dataFim,

        @NotNull(message = "É obrigatório selecionar um professor.")
        Integer professorId,

        @NotNull(message = "É obrigatório selecionar uma categoria.")
        Integer categoriaId
) {
}