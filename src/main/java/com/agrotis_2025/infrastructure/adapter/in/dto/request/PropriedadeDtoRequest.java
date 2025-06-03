package com.agrotis_2025.infrastructure.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record PropriedadeDtoRequest(

        @Schema(name = "propriedadeId", description = "Identificador único do recurso.",
                example = "034eb74c-69ee-4bd4-a064-5c4cc5e9e748")
        UUID propriedadeId
) {
}

