package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationAnswer;

@Repository
public interface EvaluationAnswerRepository extends JpaRepository<EvaluationAnswer, Long> {

    List<EvaluationAnswer> findByIsActiveTrue();
}