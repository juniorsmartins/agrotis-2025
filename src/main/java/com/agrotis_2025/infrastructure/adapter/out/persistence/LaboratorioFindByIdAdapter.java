package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.application.port.output.LaboratorioFindByIdOutputPort;
import com.agrotis_2025.domain.exception.http404.LaboratorioNotFoundException;
import com.agrotis_2025.domain.model.Laboratorio;
import com.agrotis_2025.infrastructure.adapter.out.mapper.PersistenceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LaboratorioFindByIdAdapter implements LaboratorioFindByIdOutputPort {

    private final LaboratorioRepository repository;

    private final PersistenceMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public Laboratorio findById(@NonNull final UUID id) {
        return repository.findById(id)
                .map(mapper::toLaboratorioIn)
                .orElseThrow(() -> new LaboratorioNotFoundException(id));
    }
}

