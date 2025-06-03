package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.application.port.output.ClienteCreateOutputPort;
import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.infrastructure.adapter.out.mapper.PersistenceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteCreateAdapter implements ClienteCreateOutputPort {

    private final PersistenceMapper mapper;

    private final ClienteRepository repository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Cliente create(@NonNull final Cliente cliente) {
        return Optional.of(cliente)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomainIn)
                .orElseThrow(RuntimeException::new);
    }
}

