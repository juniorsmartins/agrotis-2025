package com.agrotis_2025.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Cliente {

    private UUID clienteId;

    private String nome;

    private String observacoes;
}

