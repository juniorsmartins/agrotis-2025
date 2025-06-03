package com.agrotis_2025.domain.exception.http500;

import java.io.Serial;

public final class InternalServerProblemException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalServerProblemException() {
        super("exception.internal.server.error");
    }
}

