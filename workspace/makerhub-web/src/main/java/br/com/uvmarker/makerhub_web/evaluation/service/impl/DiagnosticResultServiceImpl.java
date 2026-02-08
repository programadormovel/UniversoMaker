package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Classification;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.DiagnosticResult;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Evaluation;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.ClassificationRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.DiagnosticResultRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.DiagnosticResultDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.DiagnosticResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiagnosticResultServiceImpl implements DiagnosticResultService {

    private final DiagnosticResultRepository diagnosticResultRepository;
    private final CentralMapperEvaluation mapper;
    private final EvaluationRepository evaluationRepository;
    private final ClassificationRepository classificationRepository;

    @Override
    public List<DiagnosticResultDTO> findAll() {
        return mapper.toDiagnosticResultDTOList(diagnosticResultRepository.findAll());
    }

    @Override
    public List<DiagnosticResultDTO> findAllActive() {
        return mapper.toDiagnosticResultDTOList(diagnosticResultRepository.findByIsActiveTrue());
    }

    @Override
    public DiagnosticResultDTO findById(Long id) {
        DiagnosticResult diagnosticResult = diagnosticResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resultado diagnóstico não encontrado com ID: " + id));
        return mapper.toDiagnosticResultDTO(diagnosticResult);
    }

    @Override
    public DiagnosticResultDTO save(DiagnosticResultDTO dto) {
        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getEvaluationId()));

        Classification classification = classificationRepository.findById(dto.getClassificationId())
                .orElseThrow(() -> new EntityNotFoundException(
                "Classificação não encontrada com ID: " + dto.getClassificationId()));

        DiagnosticResult entity = mapper.toDiagnosticResult(dto);
        entity.setEvaluation(evaluation);
        entity.setClassification(classification);

        DiagnosticResult saved = diagnosticResultRepository.save(entity);
        return mapper.toDiagnosticResultDTO(saved);
    }

    @Override
    public DiagnosticResultDTO update(Long id, DiagnosticResultDTO dto) {
        DiagnosticResult existing = diagnosticResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resultado diagnóstico não encontrado com ID: " + id));

        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getEvaluationId()));

        Classification classification = classificationRepository.findById(dto.getClassificationId())
                .orElseThrow(() -> new EntityNotFoundException(
                "Classificação não encontrada com ID: " + dto.getClassificationId()));

        existing.setEvaluation(evaluation);
        existing.setClassification(classification);
        existing.setRecommendations(dto.getRecommendations());

        DiagnosticResult updated = diagnosticResultRepository.save(existing);
        return mapper.toDiagnosticResultDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        DiagnosticResult diagnosticResult = diagnosticResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resultado diagnóstico não encontrado com ID: " + id));
        diagnosticResult.setIsActive(false);
        diagnosticResultRepository.save(diagnosticResult);
    }
}
