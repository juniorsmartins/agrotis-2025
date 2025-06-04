package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.application.port.output.LaboratorioSearchOutputPort;
import com.agrotis_2025.infrastructure.adapter.in.filter.LaboratorioFiltroDto;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.LaboratorioEntity;
import com.agrotis_2025.infrastructure.adapter.out.persistence.spec.LaboratorioSpecification;
import com.agrotis_2025.infrastructure.adapter.out.persistence.spec.PropriedadeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LaboratorioSearchAdapter implements LaboratorioSearchOutputPort {

    private final LaboratorioRepository repository;

    @Override
    public Page<LaboratorioEntity> search(final LaboratorioFiltroDto filtroDto, final Pageable paginacao) {

        return Optional.of(filtroDto)
                .map(filtro -> repository
                        .findAll(LaboratorioSpecification.consultaDinamica(filtroDto), paginacao))
                .orElseThrow();
    }
}

