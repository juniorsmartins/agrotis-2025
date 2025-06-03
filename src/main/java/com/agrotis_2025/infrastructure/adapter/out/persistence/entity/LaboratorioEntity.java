package com.agrotis_2025.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "laboratorios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"laboratorioId"})
public final class LaboratorioEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "laboratorio_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID laboratorioId;

    @Column(name = "nome", nullable = false)
    private String nome;
}

