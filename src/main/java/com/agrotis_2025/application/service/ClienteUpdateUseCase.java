package com.agrotis_2025.application.service;

import com.agrotis_2025.application.port.input.ClienteUpdateInputPort;
import com.agrotis_2025.application.port.output.ClienteCreateOutputPort;
import com.agrotis_2025.application.port.output.ClienteFindByIdOutputPort;
import com.agrotis_2025.application.port.output.LaboratorioFindByIdOutputPort;
import com.agrotis_2025.application.port.output.PropriedadeFindByIdOutputPort;
import com.agrotis_2025.domain.exception.http404.ClienteNotFoundException;
import com.agrotis_2025.domain.model.Cliente;
import com.agrotis_2025.infrastructure.adapter.out.mapper.PersistenceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteUpdateUseCase implements ClienteUpdateInputPort {

    private final ClienteFindByIdOutputPort clienteFindByIdOutputPort;

    private final ClienteCreateOutputPort clienteCreateOutputPort;

    private final PropriedadeFindByIdOutputPort propriedadeFindByIdOutputPort;

    private final LaboratorioFindByIdOutputPort laboratorioFindByIdOutputPort;

    @Override
    public Cliente update(@NonNull UUID id, @NonNull Cliente clienteUpdate) {

        return Optional.ofNullable(clienteFindByIdOutputPort.findById(id))
                .map(clienteDoBanco -> atualizarPropriedade(clienteDoBanco, clienteUpdate))
                .map(clienteDoBanco -> atualizarLaboratorio(clienteDoBanco, clienteUpdate))
                .map(clienteDoBanco -> atualizarCliente(clienteDoBanco, clienteUpdate))
                .map(clienteCreateOutputPort::create)
                .orElseThrow();
    }

    private Cliente atualizarPropriedade(Cliente clienteDoBanco, Cliente clienteUpdate) {

        var propriedade = propriedadeFindByIdOutputPort
                .findById(clienteUpdate.getPropriedade().getPropriedadeId());

        clienteDoBanco.setPropriedade(propriedade);

        return clienteDoBanco;
    }

    private Cliente atualizarLaboratorio(Cliente clienteDoBanco, Cliente clienteUpdate) {

        var laboratorio = laboratorioFindByIdOutputPort
                .findById(clienteUpdate.getLaboratorio().getLaboratorioId());

        clienteDoBanco.setLaboratorio(laboratorio);

        return clienteDoBanco;
    }

    private Cliente atualizarCliente(Cliente clienteDoBanco, Cliente clienteUpdate) {
        BeanUtils.copyProperties(clienteUpdate, clienteDoBanco, "clienteId", "propriedade", "laboratorio");
        return clienteDoBanco;
    }
}

