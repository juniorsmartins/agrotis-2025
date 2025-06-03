package com.agrotis_2025.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "PropriedadeDtoResponse", description = "Transportador de dados de saída.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PropriedadeDtoResponse(

        @Schema(name = "propriedadeId", description = "Identificador único do recurso.",
                example = "034eb74c-69ee-4bd4-a064-5c4cc5e9e748")
        UUID propriedadeId,

        @Schema(name = "Nome", description = "Nome da propriedade.", example = "Fazenda XPTO")
        String nome
) {
}
