package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.application.port.output.PropriedadeFindByIdOutputPort;
import com.agrotis_2025.domain.exception.http404.PropriedadeNotFoundException;
import com.agrotis_2025.domain.model.Propriedade;
import com.agrotis_2025.infrastructure.adapter.out.mapper.PersistenceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PropriedadeFindByIdAdapter implements PropriedadeFindByIdOutputPort {

    private final PropriedadeRepository repository;

    private final PersistenceMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public Propriedade findById(@NonNull final UUID id) {
        return repository.findById(id)
                .map(mapper::toPropriedadeIn)
                .orElseThrow(() -> new PropriedadeNotFoundException(id));
    }
}

