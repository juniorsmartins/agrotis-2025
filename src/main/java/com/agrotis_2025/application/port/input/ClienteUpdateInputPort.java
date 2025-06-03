package com.agrotis_2025.application.port.input;

import com.agrotis_2025.domain.model.Cliente;

import java.util.UUID;

public interface ClienteUpdateInputPort {

    Cliente update(UUID id, Cliente cliente);
}

