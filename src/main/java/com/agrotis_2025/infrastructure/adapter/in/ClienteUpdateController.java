package com.agrotis_2025.infrastructure.adapter.in;

import com.agrotis_2025.application.port.input.ClienteUpdateInputPort;
import com.agrotis_2025.domain.exception.http500.InternalServerProblemException;
import com.agrotis_2025.infrastructure.adapter.in.dto.request.ClienteDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.ClienteDtoResponse;
import com.agrotis_2025.infrastructure.adapter.in.mapper.RestMapper;
import com.agrotis_2025.infrastructure.constant.ConstantsController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Clientes", description = "Contém recursos de atualizar.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {ConstantsController.URI_CLIENTE})
public class ClienteUpdateController {

    private final RestMapper restMapper;

    private final ClienteUpdateInputPort updateInputPort;

    @PutMapping(path = {"/{id}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Atualizar", description = "Modificar dados de um recurso.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteDtoResponse.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    ),
                    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    ),
                    @ApiResponse(responseCode = "409", description = "Conflict - violação de regras de negócio.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    ),
            }
    )
    public ResponseEntity<ClienteDtoResponse> update(
            @Parameter(name = "id", description = "Identificador único do recurso.",
                    example = "034eb74c-69ee-4bd4-a064-5c4cc5e9e748", required = true)
            @PathVariable(name = "id") final UUID id,
            @Parameter(name = "DtoRequest", description = "Para transporte de dados de entrada.", required = true)
            @RequestBody @Valid ClienteDtoRequest dtoRequest) {

        var response = Optional.ofNullable(dtoRequest)
                .map(restMapper::toDomain)
                .map(domain -> updateInputPort.update(id, domain))
                .map(restMapper::toResponse)
                .orElseThrow(() -> {
                    log.error("ClienteUpdateController - Erro interno do servidor no método update.");
                    return new InternalServerProblemException();
                });

        return ResponseEntity
                .ok()
                .body(response);
    }
}

