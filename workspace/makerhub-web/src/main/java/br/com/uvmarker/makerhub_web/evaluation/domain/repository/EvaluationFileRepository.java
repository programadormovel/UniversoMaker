package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationFile;

@Repository
public interface EvaluationFileRepository extends JpaRepository<EvaluationFile, Long> {

    List<EvaluationFile> findByIsActiveTrue();

    List<EvaluationFile> findByEvaluation_IdAndIsActiveTrue(Long evaluationId);
    
}