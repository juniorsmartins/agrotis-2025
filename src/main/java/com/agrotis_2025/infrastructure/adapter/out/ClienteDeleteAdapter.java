package com.agrotis_2025.infrastructure.adapter.out;

import com.agrotis_2025.application.port.output.ClienteDeleteOutputPort;
import com.agrotis_2025.domain.exception.http404.ClienteNotFoundException;
import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.infrastructure.adapter.out.persistence.ClienteRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ClienteDeleteAdapter implements ClienteDeleteOutputPort {

    private final ClienteRepository repository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void deleteById(@NonNull final UUID id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete, () -> {
                    log.error("ClienteDeleteAdapter - Cliente não encontrado por id: {}.", id);
                    throw new ClienteNotFoundException(id);
                });
    }
}

