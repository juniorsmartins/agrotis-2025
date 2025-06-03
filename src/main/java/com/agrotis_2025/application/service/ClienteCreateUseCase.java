package com.agrotis_2025.application.service;

import com.agrotis_2025.application.port.input.ClienteCreateInputPort;
import com.agrotis_2025.application.port.output.ClienteCreateOutputPort;
import com.agrotis_2025.application.port.output.PropriedadeFindByIdOutputPort;
import com.agrotis_2025.domain.model.Cliente;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteCreateUseCase implements ClienteCreateInputPort {

    private final PropriedadeFindByIdOutputPort propriedadeFindByIdOutputPort;

    private final ClienteCreateOutputPort outputPort;

    @Override
    public Cliente create(@NonNull final Cliente cliente) {

        return Optional.of(cliente)
                .map(this::verificarPropriedade)
                .map(outputPort::create)
                .orElseThrow(RuntimeException::new);
    }

    private Cliente verificarPropriedade(Cliente cliente) {

        var propriedade = propriedadeFindByIdOutputPort.findById(cliente.getPropriedade().getPropriedadeId());
        cliente.setPropriedade(propriedade);

        return cliente;
    }
}

