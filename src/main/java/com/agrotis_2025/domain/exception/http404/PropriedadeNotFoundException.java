package com.agrotis_2025.domain.exception.http404;

import java.io.Serial;
import java.util.UUID;

public final class PropriedadeNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PropriedadeNotFoundException(final UUID id) {
        super("exception.resource.not.found.propriedade", id);
    }
}

