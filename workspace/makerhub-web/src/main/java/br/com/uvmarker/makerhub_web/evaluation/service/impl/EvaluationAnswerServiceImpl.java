package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Evaluation;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationAnswer;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationQuestion;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationAnswerRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationQuestionRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationAnswerDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationAnswerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationAnswerServiceImpl implements EvaluationAnswerService {

    private final EvaluationAnswerRepository evaluationAnswerRepository;
    private final CentralMapperEvaluation mapper;
    private final EvaluationRepository evaluationRepository;
    private final EvaluationQuestionRepository evaluationQuestionRepository;

    @Override
    public List<EvaluationAnswerDTO> findAll() {
        return mapper.toEvaluationAnswerDTOList(evaluationAnswerRepository.findAll());
    }

    @Override
    public List<EvaluationAnswerDTO> findAllActive() {
        return mapper.toEvaluationAnswerDTOList(evaluationAnswerRepository.findByIsActiveTrue());
    }

    @Override
    public EvaluationAnswerDTO findById(Long id) {
        EvaluationAnswer evaluationAnswer = evaluationAnswerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta de avaliação não encontrada com ID: " + id));
        return mapper.toEvaluationAnswerDTO(evaluationAnswer);
    }

    @Override
    public EvaluationAnswerDTO save(EvaluationAnswerDTO dto) {
        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getEvaluationId()));

        EvaluationQuestion question = evaluationQuestionRepository.findById(dto.getQuestionId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Questão não encontrada com ID: " + dto.getQuestionId()));

        EvaluationAnswer entity = mapper.toEvaluationAnswer(dto);
        entity.setEvaluation(evaluation);
        entity.setQuestion(question);

        EvaluationAnswer saved = evaluationAnswerRepository.save(entity);
        return mapper.toEvaluationAnswerDTO(saved);
    }

    @Override
    public EvaluationAnswerDTO update(Long id, EvaluationAnswerDTO dto) {
        EvaluationAnswer existing = evaluationAnswerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta de avaliação não encontrada com ID: " + id));

        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getEvaluationId()));

        EvaluationQuestion question = evaluationQuestionRepository.findById(dto.getQuestionId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Questão não encontrada com ID: " + dto.getQuestionId()));

        existing.setEvaluation(evaluation);
        existing.setQuestion(question);
        existing.setAnswerText(dto.getAnswerText());
        existing.setScore(dto.getScore());

        EvaluationAnswer updated = evaluationAnswerRepository.save(existing);
        return mapper.toEvaluationAnswerDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        EvaluationAnswer evaluationAnswer = evaluationAnswerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta de avaliação não encontrada com ID: " + id));
        evaluationAnswer.setIsActive(false);
        evaluationAnswerRepository.save(evaluationAnswer);
    }
}
