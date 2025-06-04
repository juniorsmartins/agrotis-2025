package com.agrotis_2025.infrastructure.adapter.in.mapper;

import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.domain.model.Laboratorio;
import com.agrotis_2025.domain.model.Propriedade;
import com.agrotis_2025.infrastructure.adapter.in.dto.request.ClienteDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.request.LaboratorioDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.request.PropriedadeDtoRequest;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.ClienteDtoResponse;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.LaboratorioDtoResponse;
import com.agrotis_2025.infrastructure.adapter.in.dto.response.PropriedadeDtoResponse;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.PropriedadeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestMapper {

    @Mapping(target = "clienteId", ignore = true)
    Cliente toDomain(ClienteDtoRequest dtoRequest);

    ClienteDtoResponse toResponse(Cliente cliente);

    @Mapping(target = "nome", ignore = true)
    Propriedade toPropriedade(PropriedadeDtoRequest dtoRequest);

    PropriedadeDtoResponse toPropriedadeDtoResponse(Propriedade propriedade);

    PropriedadeDtoResponse toDtoResponse(PropriedadeEntity propriedade);

    @Mapping(target = "nome", ignore = true)
    Laboratorio toLaboratorio(LaboratorioDtoRequest dtoRequest);

    LaboratorioDtoResponse toLaboratorioDtoResponse(Laboratorio laboratorio);

    default Page<PropriedadeDtoResponse> toPageResponse(Page<PropriedadeEntity> entityPage) {
        List<PropriedadeDtoResponse> dtos = entityPage.getContent()
                .stream()
                .map(this::toDtoResponse)
                .toList();
        return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalElements());
    }
}

