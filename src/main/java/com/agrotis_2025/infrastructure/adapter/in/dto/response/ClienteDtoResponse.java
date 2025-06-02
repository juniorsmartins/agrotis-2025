package com.agrotis_2025.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import static com.agrotis_2025.infrastructure.constant.ConstantsValidation.MAX_CARACTER_OBSERVACOES;

@Schema(name = "ClienteDtoResponse", description = "Transportador de dados de saída.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDtoResponse(

        @Schema(name = "clienteId", description = "Identificador único do recurso.",
                example = "034eb74c-69ee-4bd4-a064-5c4cc5e9e748")
        UUID clienteId,

        @Schema(name = "Nome", description = "Nome do cliente.", example = "Robert Martin")
        @NotBlank
        String nome,

        @Schema(name = "Observações", description = "Espaço para informações adicionais.", example = "Observação de teste")
        @NotBlank
        @Size(max = MAX_CARACTER_OBSERVACOES)
        String observacoes
) {
}

