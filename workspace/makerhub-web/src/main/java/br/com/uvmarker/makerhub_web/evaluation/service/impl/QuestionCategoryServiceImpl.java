package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.QuestionCategory;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationTypeRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.QuestionCategoryRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.QuestionCategoryDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.QuestionCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

     private final QuestionCategoryRepository questionCategoryRepository;
    private final EvaluationTypeRepository evaluationTypeRepository;
    private final CentralMapperEvaluation mapper;

    @Override
    public List<QuestionCategoryDTO> findAll() {
        return mapper.toQuestionCategoryDTOList(questionCategoryRepository.findAll());
    }

    @Override
    public List<QuestionCategoryDTO> findAllActive() {
        return mapper.toQuestionCategoryDTOList(questionCategoryRepository.findByIsActiveTrue());
    }

    @Override
    public QuestionCategoryDTO findById(Long id) {
        QuestionCategory category = questionCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria de Questão não encontrada com ID: " + id));
        return mapper.toQuestionCategoryDTO(category);
    }

    @Override
    public List<QuestionCategoryDTO> findByTypeId(Long typeId) {
        return mapper.toQuestionCategoryDTOList(questionCategoryRepository.findByType_IdAndIsActiveTrue(typeId));
    }

    @Override
    public QuestionCategoryDTO save(QuestionCategoryDTO dto) {
        EvaluationType type = evaluationTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de avaliação não encontrado com ID: " + dto.getTypeId()));

        QuestionCategory entity = mapper.toQuestionCategory(dto);
        entity.setType(type);
        entity.setIsActive(true);
        entity.setCreatedAt(LocalDateTime.now());

        QuestionCategory saved = questionCategoryRepository.save(entity);
        return mapper.toQuestionCategoryDTO(saved);
    }

    @Override
    public QuestionCategoryDTO update(Long id, QuestionCategoryDTO dto) {
        QuestionCategory existing = questionCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria de Questão não encontrada com ID: " + id));

        EvaluationType type = evaluationTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de avaliação não encontrado com ID: " + dto.getTypeId()));

        existing.setType(type);
        existing.setCategoryName(dto.getCategoryName());
        existing.setDescription(dto.getDescription());
    
        QuestionCategory updated = questionCategoryRepository.save(existing);
        return mapper.toQuestionCategoryDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        QuestionCategory category = questionCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria de Questão não encontrada com ID: " + id));
        category.setIsActive(false);
        questionCategoryRepository.save(category);
    }

}
