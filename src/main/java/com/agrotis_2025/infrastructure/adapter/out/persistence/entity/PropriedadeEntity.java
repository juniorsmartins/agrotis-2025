package com.agrotis_2025.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "propriedades")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"propriedadeId"})
public final class PropriedadeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "propriedade_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID propriedadeId;

    @Column(name = "nome", nullable = false)
    private String nome;
}

