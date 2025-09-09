package com.universomaker.repository;

import com.universomaker.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    List<Aluno> findByAtivoTrue();
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}