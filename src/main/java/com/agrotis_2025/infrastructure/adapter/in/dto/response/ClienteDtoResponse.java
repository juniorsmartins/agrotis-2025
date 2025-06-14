package com.agrotis_2025.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;
import java.util.UUID;

@Schema(name = "ClienteDtoResponse", description = "Transportador de dados de saída.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDtoResponse(

        @Schema(name = "clienteId", description = "Identificador único do recurso.",
                example = "034eb74c-69ee-4bd4-a064-5c4cc5e9e748")
        UUID clienteId,

        @Schema(name = "Nome", description = "Nome do cliente.", example = "Robert Martin")
        String nome,

        @Schema(name = "DataInicial", description = "Data de início.", example = "2025-06-05T09:00:00Z")
        ZonedDateTime dataInicial,

        @Schema(name = "DataFinal", description = "Data de fim.", example = "2025-06-05T09:00:00Z")
        ZonedDateTime dataFinal,

        @Schema(name = "Propriedade", description = "Relação do Cliente com a Propriedade.")
        PropriedadeDtoResponse propriedade,

        @Schema(name = "Laboratório", description = "Relação do Cliente com a Laboratório.")
        LaboratorioDtoResponse laboratorio,

        @Schema(name = "Observações", description = "Espaço para informações adicionais.",
                example = "Observação de teste")
        String observacoes
) {
}

