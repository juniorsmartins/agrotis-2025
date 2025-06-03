package com.agrotis_2025.application.port.output;

import com.agrotis_2025.domain.model.Propriedade;

import java.util.UUID;

public interface PropriedadeFindByIdOutputPort {

    Propriedade findById(UUID id);
}

