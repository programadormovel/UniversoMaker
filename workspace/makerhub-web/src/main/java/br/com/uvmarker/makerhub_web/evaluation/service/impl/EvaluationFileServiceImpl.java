package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Evaluation;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationFile;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationFileRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationFileDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationFileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationFileServiceImpl implements EvaluationFileService {

    private final EvaluationFileRepository evaluationFileRepository;
    private final CentralMapperEvaluation mapper;
    private final EvaluationRepository evaluationRepository;

    @Override
    public List<EvaluationFileDTO> findAll() {
        return mapper.toEvaluationFileDTOList(evaluationFileRepository.findAll());
    }

    @Override
    public List<EvaluationFileDTO> findAllActive() {
        return mapper.toEvaluationFileDTOList(evaluationFileRepository.findByIsActiveTrue());
    }

    @Override
    public EvaluationFileDTO findById(Long id) {
        EvaluationFile evaluationFile = evaluationFileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo de avaliação não encontrado com ID: " + id));
        return mapper.toEvaluationFileDTO(evaluationFile);
    }

    @Override
    public EvaluationFileDTO save(EvaluationFileDTO dto) {
        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getEvaluationId()));

        EvaluationFile entity = mapper.toEvaluationFile(dto);
        entity.setEvaluation(evaluation);
  
        EvaluationFile saved = evaluationFileRepository.save(entity);
        return mapper.toEvaluationFileDTO(saved);
    }

    @Override
    public EvaluationFileDTO update(Long id, EvaluationFileDTO dto) {
        EvaluationFile existing = evaluationFileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo de avaliação não encontrado com ID: " + id));

        Evaluation evaluation = evaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Avaliação não encontrada com ID: " + dto.getEvaluationId()));

        existing.setEvaluation(evaluation);
        existing.setFileUrl(dto.getFileUrl());
        existing.setDescription(dto.getDescription());

        EvaluationFile updated = evaluationFileRepository.save(existing);
        return mapper.toEvaluationFileDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        EvaluationFile evaluationFile = evaluationFileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arquivo de avaliação não encontrado com ID: " + id));
        evaluationFile.setIsActive(false);
        evaluationFileRepository.save(evaluationFile);
    }

    @Override
    public List<EvaluationFileDTO> findAllActiveByEvaluationId(Long evaluationId) {
        return mapper.toEvaluationFileDTOList(
            evaluationFileRepository.findByEvaluation_IdAndIsActiveTrue(evaluationId));
    }
}
