package com.agrotis_2025.application.service;

import com.agrotis_2025.application.port.input.ClienteCreateInputPort;
import com.agrotis_2025.application.port.output.ClienteCreateOutputPort;
import com.agrotis_2025.domain.model.Cliente;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteCreateUseCase implements ClienteCreateInputPort {

    private final ClienteCreateOutputPort outputPort;

    @Override
    public Cliente create(@NonNull final Cliente cliente) {

        return outputPort.create(cliente);
    }
}

