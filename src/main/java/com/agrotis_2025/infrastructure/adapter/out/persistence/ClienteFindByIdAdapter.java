package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.application.port.output.ClienteFindByIdOutputPort;
import com.agrotis_2025.domain.exception.http404.ClienteNotFoundException;
import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.infrastructure.adapter.out.mapper.PersistenceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClienteFindByIdAdapter implements ClienteFindByIdOutputPort {

    private final PersistenceMapper mapper;

    private final ClienteRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(@NonNull final UUID id) {
        return repository.findById(id)
                .map(mapper::toDomainIn)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }
}

