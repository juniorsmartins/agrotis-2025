package com.agrotis_2025.domain.exception.http404;

import lombok.Getter;

import java.io.Serial;
import java.util.UUID;

@Getter
public abstract sealed class ResourceNotFoundException extends RuntimeException
        permits ClienteNotFoundException, LaboratorioNotFoundException, PropriedadeNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    private final UUID id;

    protected ResourceNotFoundException(final String messageKey, final UUID id) {
        super(messageKey);
        this.messageKey = messageKey;
        this.id = id;
    }
}

