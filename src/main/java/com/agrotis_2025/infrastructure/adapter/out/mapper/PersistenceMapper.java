package com.agrotis_2025.infrastructure.adapter.out.mapper;

import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.domain.model.Laboratorio;
import com.agrotis_2025.domain.model.Propriedade;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.LaboratorioEntity;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.PropriedadeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    ClienteEntity toEntity(Cliente cliente);

    Cliente toDomainIn(ClienteEntity entity);

    PropriedadeEntity toPropriedadeEntity(Propriedade propriedade);

    Propriedade toPropriedadeIn(PropriedadeEntity entity);

    LaboratorioEntity toLaboratorioEntity(Laboratorio laboratorio);

    Laboratorio toLaboratorioIn(LaboratorioEntity entity);
}

