package com.universomaker.repository;

import com.universomaker.entity.PEI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PEIRepository extends JpaRepository<PEI, UUID> {
    List<PEI> findByAlunoId(UUID alunoId);
    List<PEI> findByStatus(PEI.Status status);
}