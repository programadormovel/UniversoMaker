package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findByIsActiveTrue();

    List<Evaluation> findByStudentIdAndIsActiveTrue(Long studentId);

    List<Evaluation> findByProfessionalIdAndIsActiveTrue(Long professionalId);
}