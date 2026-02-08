package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationTypeRepository;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationTypeServiceImpl implements EvaluationTypeService {

    private final EvaluationTypeRepository evaluationTypeRepository;

    @Override
    public List<EvaluationType> findAll() {
        return evaluationTypeRepository.findAll();
    }

    @Override
    public List<EvaluationType> findAllActive() {
        return evaluationTypeRepository.findByIsActiveTrue();
    }

    @Override
    public EvaluationType findById(Long id) {
        return evaluationTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de avaliação não encontrado com ID: " + id));
    }

    @Override
    public EvaluationType save(EvaluationType evaluationType) {
        EvaluationType evaluationTypeToSave = EvaluationType.builder()
                .typeName(evaluationType.getTypeName())
                .description(evaluationType.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return evaluationTypeRepository.save(evaluationTypeToSave);
    }

    @Override
    public EvaluationType inactivateById(Long id) {
        EvaluationType evaluationType = evaluationTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
        evaluationType.setIsActive(false);
        return evaluationTypeRepository.save(evaluationType);
    }

}
