package com.agrotis_2025.infrastructure.adapter.in.mapper;

import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.infrastructure.adapter.in.dto.request.ClienteDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.ClienteDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestMapper {

    @Mapping(target = "clienteId", ignore = true)
    Cliente toDomain(ClienteDtoRequest dtoRequest);

    ClienteDtoResponse toResponse(Cliente cliente);
}

