package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.application.port.output.PropriedadeSearchOutputPort;
import com.agrotis_2025.infrastructure.adapter.in.filter.PropriedadeFiltroDto;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.PropriedadeEntity;
import com.agrotis_2025.infrastructure.adapter.out.persistence.spec.PropriedadeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PropriedadeSearchAdapter implements PropriedadeSearchOutputPort {

    private final PropriedadeRepository repository;

    @Override
    public Page<PropriedadeEntity> search(final PropriedadeFiltroDto filtroDto, final Pageable paginacao) {

        return Optional.of(filtroDto)
                .map(filtro -> repository
                        .findAll(PropriedadeSpecification.consultaDinamica(filtroDto), paginacao))
                .orElseThrow();
    }
}

