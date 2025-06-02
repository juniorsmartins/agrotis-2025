package com.agrotis_2025.infrastructure.adapter.out.mapper;

import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    ClienteEntity toEntity(Cliente cliente);

    Cliente toDomainIn(ClienteEntity entity);
}

