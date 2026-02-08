package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.DiagnosticResult;

@Repository
public interface DiagnosticResultRepository extends JpaRepository<DiagnosticResult, Long> {

    List<DiagnosticResult> findByIsActiveTrue();
}