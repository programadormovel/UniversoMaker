package br.com.uvmarker.makerhub_web.pei.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EvaluationFunctionPei;

@Repository
public interface EvaluationFunctionPeiRepository extends JpaRepository<EvaluationFunctionPei, Long> {

    List<EvaluationFunctionPei> findByIsActiveTrue();

    List<EvaluationFunctionPei> findByPei_IdAndIsActiveTrue(Long peiId);
}