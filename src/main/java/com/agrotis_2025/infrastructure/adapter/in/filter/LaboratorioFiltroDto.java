package com.agrotis_2025.infrastructure.adapter.in.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LaboratorioFiltroDto", description = "Transportador de dados de entrada.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LaboratorioFiltroDto(

        @Schema(name = "laboratorioId", description = "Identificador único do recurso.",
                example = "034eb74c-69ee-4bd4-a064-5c4cc5e9e748")
        String laboratorioId,

        @Schema(name = "Nome", description = "Nome do laboratório.", example = "Laboratório XPTO")
        String nome
) {
}

