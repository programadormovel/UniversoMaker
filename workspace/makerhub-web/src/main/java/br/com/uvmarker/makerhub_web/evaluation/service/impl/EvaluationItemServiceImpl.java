package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationItem;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationItemRepository;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationItemServiceImpl implements EvaluationItemService {

    private final EvaluationItemRepository evaluationItemRepository;

    @Override
    public List<EvaluationItem> findAll() {
        return evaluationItemRepository.findAll();
    }

    @Override
    public List<EvaluationItem> findAllActive() {
        return evaluationItemRepository.findByIsActiveTrue();
    }

    @Override
    public EvaluationItem findById(Long id) {
        return evaluationItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de avaliação não encontrado com ID: " + id));
    }

    @Override
    public EvaluationItem save(EvaluationItem evaluationItem) {
        EvaluationItem evaluationItemToSave = EvaluationItem.builder()
                .itemName(evaluationItem.getItemName())
                .description(evaluationItem.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return evaluationItemRepository.save(evaluationItemToSave);
    }

    @Override
    public EvaluationItem inactivateById(Long id) {
        EvaluationItem evaluationItem = evaluationItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de contato não encontrado com ID: " + id));
        evaluationItem.setIsActive(false);
        return evaluationItemRepository.save(evaluationItem);
    }

}
