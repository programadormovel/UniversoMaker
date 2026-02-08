package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;

@Repository
public interface EvaluationTypeRepository extends JpaRepository<EvaluationType, Long> {

    List<EvaluationType> findByIsActiveTrue();
}