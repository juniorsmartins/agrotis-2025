package com.agrotis_2025.infrastructure.adapter.in.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;

import static com.agrotis_2025.infrastructure.constant.ConstantsValidation.MAX_CARACTER_OBSERVACOES;

@Schema(name = "ClienteDtoRequest", description = "Transportador de dados de entrada.")
public record ClienteDtoRequest(

        @Schema(name = "Nome", description = "Nome do cliente.", example = "Robert Martin")
        @NotBlank
        String nome,

        @Schema(name = "DataInicial", description = "Data de início.", example = "2025-06-05T09:00:00Z")
        @NotNull
        ZonedDateTime dataInicial,

        @Schema(name = "DataFinal", description = "Data de fim.", example = "2025-06-05T09:00:00Z")
        @NotNull
        ZonedDateTime dataFinal,

        @Schema(name = "Propriedade", description = "Relação do Cliente com a Propriedade.")
        @NotNull
        @Valid
        PropriedadeDtoRequest propriedade,

        @Schema(name = "Laboratório", description = "Relação do Cliente com o Laboratório.")
        @NotNull
        @Valid
        LaboratorioDtoRequest laboratorio,

        @Schema(name = "Observações", description = "Espaço para informações adicionais.",
                example = "Observação de teste")
        @NotBlank
        @Size(max = MAX_CARACTER_OBSERVACOES)
        String observacoes
) {
}

