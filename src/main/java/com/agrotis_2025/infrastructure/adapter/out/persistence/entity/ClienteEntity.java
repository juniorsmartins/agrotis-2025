package com.agrotis_2025.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.agrotis_2025.infrastructure.constant.ConstantsValidation.MAX_CARACTER_OBSERVACOES;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"clienteId"})
public final class ClienteEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cliente_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID clienteId;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataInicial", nullable = false)
    private ZonedDateTime dataInicial;

    @Column(name = "dataFinal", nullable = false)
    private ZonedDateTime dataFinal;

    @OneToOne
    @JoinColumn(name = "propriedade_id")
    private PropriedadeEntity propriedade;

    @OneToOne
    @JoinColumn(name = "laboratorio_id")
    private LaboratorioEntity laboratorio;

    @Column(name = "observacoes", length = MAX_CARACTER_OBSERVACOES)
    private String observacoes;
}

