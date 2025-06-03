package com.agrotis_2025.application.service;

import com.agrotis_2025.application.port.input.ClienteDeleteByIdInputPort;
import com.agrotis_2025.application.port.output.ClienteDeleteOutputPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteDeleteUseCase implements ClienteDeleteByIdInputPort {

    private final ClienteDeleteOutputPort deleteOutputPort;

    @Override
    public void deleteById(@NonNull final UUID id) {
        deleteOutputPort.deleteById(id);
    }
}

