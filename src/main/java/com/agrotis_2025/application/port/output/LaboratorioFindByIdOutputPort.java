package com.agrotis_2025.application.port.output;

import com.agrotis_2025.domain.model.Laboratorio;

import java.util.UUID;

public interface LaboratorioFindByIdOutputPort {

    Laboratorio findById(UUID id);
}

