package com.agrotis_2025.infrastructure.adapter.in.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import static com.agrotis_2025.infrastructure.constant.ConstantsValidation.MAX_CARACTER_OBSERVACOES;

@Schema(name = "ClienteDtoRequest", description = "Transportador de dados de entrada.")
public record ClienteDtoRequest(

        @Schema(name = "Nome", description = "Nome do cliente.", example = "Robert Martin")
        @NotBlank
        String nome,

        @Schema(name = "DataInicial", description = "Data de início.", example = "05/06/2025")
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataInicial,

        @Schema(name = "DataFinal", description = "Data de fim.", example = "10/12/2025")
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataFinal,

        @Schema(name = "Observações", description = "Espaço para informações adicionais.",
                example = "Observação de teste")
        @NotBlank
        @Size(max = MAX_CARACTER_OBSERVACOES)
        String observacoes
) {
}

