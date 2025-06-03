package com.agrotis_2025.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Cliente {

    private UUID clienteId;

    private String nome;

    private LocalDate dataInicial;

    private LocalDate dataFinal;

    private Propriedade propriedade;

    private Laboratorio laboratorio;

    private String observacoes;
}

