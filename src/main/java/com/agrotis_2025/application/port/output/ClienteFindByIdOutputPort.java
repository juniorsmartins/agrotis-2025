package com.agrotis_2025.application.port.output;

import com.agrotis_2025.domain.model.Cliente;

import java.util.UUID;

public interface ClienteFindByIdOutputPort {

    Cliente findById(UUID id);
}

