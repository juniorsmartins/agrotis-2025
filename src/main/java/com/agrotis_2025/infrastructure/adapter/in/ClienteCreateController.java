package com.agrotis_2025.infrastructure.adapter.in;

import com.agrotis_2025.application.port.input.ClienteCreateInputPort;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "Clientes", description = "Contém recursos de cadastrar.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {ConstantsController.URI_CLIENTE})
public class ClienteCreateController {

    private final RestMapper restMapper;

    private final ClienteCreateInputPort inputPort;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Cadastrar", description = "Criar um novo recurso.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created - recurso cadastrado com sucesso.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteDtoResponse.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    ),
                    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado.",
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
                    )
            }
    )
    public ResponseEntity<ClienteDtoResponse> create(
            @Parameter(name = "ClienteDtoRequest", description = "Para transporte de dados de entrada.", required = true)
            @RequestBody @Valid ClienteDtoRequest dtoRequest) {

        var response = Optional.ofNullable(dtoRequest)
                .map(restMapper::toDomain)
                .map(inputPort::create)
                .map(restMapper::toResponse)
                .orElseThrow(() -> {
                    log.error("ClienteCreateController - Erro interno do servidor no método create.");
                    return new InternalServerProblemException();
                });

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

