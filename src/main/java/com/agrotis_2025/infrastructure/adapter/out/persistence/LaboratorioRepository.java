package com.agrotis_2025.infrastructure.adapter.out.persistence;

import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.LaboratorioEntity;
import com.agrotis_2025.infrastructure.adapter.out.persistence.entity.PropriedadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LaboratorioRepository extends JpaRepository<LaboratorioEntity, UUID>,
        JpaSpecificationExecutor<LaboratorioEntity> {

    Optional<LaboratorioEntity> findByNome(String nome);
}

