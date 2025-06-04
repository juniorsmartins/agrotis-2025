package com.agrotis_2025.infrastructure.adapter.in;

import com.agrotis_2025.application.port.output.PropriedadeSearchOutputPort;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.PropriedadeDtoResponse;
import com.agrotis_2025.infrastructure.adapter.in.filter.PropriedadeFiltroDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "Propriedades", description = "Contém recurso de pesquisar.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {ConstantsController.URI_PROPRIEDADE})
public class PropriedadeSearchController {

    private final PropriedadeSearchOutputPort outputPort;

    private final RestMapper mapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Pesquisar", description = "Buscar um recurso por id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.",
                            content = {@Content(mediaType = "application/json")}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal formulada.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}
                    )
            }
    )
    public ResponseEntity<Page<PropriedadeDtoResponse>> search(
            @Parameter(name = "PropriedadeFiltroDto", description = "Estrutura de dados usada como filtro de pesquisa.", required = false)
            @Valid final PropriedadeFiltroDto filtroDto,
            @PageableDefault(sort = "propriedadeId", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

        var response = Optional.of(filtroDto)
                .map(filtro -> outputPort.search(filtro, paginacao))
                .map(mapper::toPageResponse)
                .orElseThrow();

        return ResponseEntity
                .ok()
                .body(response);
    }
}

