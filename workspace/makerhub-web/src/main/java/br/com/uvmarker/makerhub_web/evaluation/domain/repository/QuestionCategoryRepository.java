package br.com.uvmarker.makerhub_web.evaluation.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.QuestionCategory;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long> {

    List<QuestionCategory> findByIsActiveTrue();

    List<QuestionCategory> findByType(EvaluationType type);

    List<QuestionCategory> findByType_IdAndIsActiveTrue(Long typeId);
}