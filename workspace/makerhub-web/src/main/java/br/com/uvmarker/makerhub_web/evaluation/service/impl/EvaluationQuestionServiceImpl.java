package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationQuestion;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.QuestionCategory;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationQuestionRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.QuestionCategoryRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationQuestionDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationQuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationQuestionServiceImpl implements EvaluationQuestionService {

     private final EvaluationQuestionRepository evaluationQuestionRepository;
    private final CentralMapperEvaluation mapper;
    private final QuestionCategoryRepository questionCategoryRepository;

    @Override
    public List<EvaluationQuestionDTO> findAll() {
        return mapper.toEvaluationQuestionDTOList(evaluationQuestionRepository.findAll());
    }

    @Override
    public List<EvaluationQuestionDTO> findAllActive() {
        return mapper.toEvaluationQuestionDTOList(evaluationQuestionRepository.findByIsActiveTrue());
    }

    @Override
    public EvaluationQuestionDTO findById(Long id) {
        EvaluationQuestion evaluationQuestion = evaluationQuestionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão de avaliação não encontrada com ID: " + id));
        return mapper.toEvaluationQuestionDTO(evaluationQuestion);
    }

    @Override
    public EvaluationQuestionDTO save(EvaluationQuestionDTO dto) {
        QuestionCategory category = questionCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoria de questão não encontrada com ID: " + dto.getCategoryId()));

        EvaluationQuestion entity = mapper.toEvaluationQuestion(dto);
        entity.setCategory(category);
        entity.setIsActive(true);

        EvaluationQuestion saved = evaluationQuestionRepository.save(entity);
        return mapper.toEvaluationQuestionDTO(saved);
    }

    @Override
    public EvaluationQuestionDTO update(Long id, EvaluationQuestionDTO dto) {
        EvaluationQuestion existing = evaluationQuestionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão de avaliação não encontrada com ID: " + id));

        QuestionCategory category = questionCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoria de questão não encontrada com ID: " + dto.getCategoryId()));

        existing.setCategory(category);
        existing.setQuestion(dto.getQuestion());

        EvaluationQuestion updated = evaluationQuestionRepository.save(existing);
        return mapper.toEvaluationQuestionDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        EvaluationQuestion evaluationQuestion = evaluationQuestionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão de avaliação não encontrada com ID: " + id));
        evaluationQuestion.setIsActive(false);
        evaluationQuestionRepository.save(evaluationQuestion);
    }
}
