package com.agrotis_2025.application.port.output;

import com.agrotis_2025.infrastructure.adapter.in.filter.PropriedadeFiltroDto;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.PropriedadeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropriedadeSearchOutputPort {

    Page<PropriedadeEntity> search(PropriedadeFiltroDto filtroDto, Pageable paginacao);
}

