package com.agrotis_2025.infrastructure.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.agrotis_2025.infrastructure.constant.ConstantsValidation.MAX_CARACTER_OBSERVACOES;

@Schema(name = "ClienteDtoRequest", description = "Transportador de dados de entrada.")
public record ClienteDtoRequest(

        @Schema(name = "Nome", description = "Nome do cliente.", example = "Robert Martin")
        @NotBlank
        String nome,

        @Schema(name = "Observações", description = "Espaço para informações adicionais.", example = "Observação de teste")
        @NotBlank
        @Size(max = MAX_CARACTER_OBSERVACOES)
        String observacoes
) {
}

