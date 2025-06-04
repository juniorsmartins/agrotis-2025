package com.agrotis_2025.application.port.output;

import com.agrotis_2025.infrastructure.adapter.in.filter.LaboratorioFiltroDto;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.LaboratorioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaboratorioSearchOutputPort {

    Page<LaboratorioEntity> search(LaboratorioFiltroDto filtroDto, Pageable paginacao);
}

