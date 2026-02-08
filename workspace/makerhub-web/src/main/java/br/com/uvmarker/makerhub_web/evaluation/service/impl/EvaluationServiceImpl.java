package br.com.uvmarker.makerhub_web.evaluation.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Evaluation;
import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationRepository;
import br.com.uvmarker.makerhub_web.evaluation.domain.repository.EvaluationTypeRepository;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationDTO;
import br.com.uvmarker.makerhub_web.evaluation.mapper.CentralMapperEvaluation;
import br.com.uvmarker.makerhub_web.evaluation.service.EvaluationService;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final CentralMapperEvaluation mapper;
    private final EvaluationTypeRepository evaluationTypeRepository;
    private final PersonService personService;

    @Override
    public List<EvaluationDTO> findAll() {
        return mapper.toEvaluationDTOList(evaluationRepository.findAll());
    }

    @Override
    public List<EvaluationDTO> findAllActive() {
        return mapper.toEvaluationDTOList(evaluationRepository.findByIsActiveTrue());
    }

    @Override
    public EvaluationDTO findById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));
        return mapper.toEvaluationDTO(evaluation);
    }

    @Override
    public EvaluationDTO save(EvaluationDTO dto) {
        EvaluationType type = evaluationTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de avaliação não encontrado com ID: " + dto.getTypeId()));

        personService.findByPersonId(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getStudentId()));

        personService.findByPersonId(dto.getProfessionalId())
                .orElseThrow(() -> new EntityNotFoundException( "Profissional não encontrado com ID: " + dto.getProfessionalId()));

        Evaluation entity = mapper.toEvaluation(dto);
        entity.setType(type);

        Evaluation saved = evaluationRepository.save(entity);
        return mapper.toEvaluationDTO(saved);
    }

    @Override
    public EvaluationDTO update(Long id, EvaluationDTO dto) {
        Evaluation existing = evaluationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));

        EvaluationType type = evaluationTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de avaliação não encontrado com ID: " + dto.getTypeId()));

        personService.findByPersonId(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getStudentId()));

        personService.findByPersonId(dto.getProfessionalId())
                .orElseThrow(() -> new EntityNotFoundException( "Profissional não encontrado com ID: " + dto.getProfessionalId()));

        existing.setType(type);
        existing.setNotes(dto.getNotes());
        existing.setRate(dto.getRate());

        Evaluation updated = evaluationRepository.save(existing);
        return mapper.toEvaluationDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + id));
        evaluation.setIsActive(false);
        evaluationRepository.save(evaluation);
    }

    @Override
    public List<EvaluationDTO> findByStudentId(Long studentId) {
        return mapper.toEvaluationDTOList(
                evaluationRepository.findByStudentIdAndIsActiveTrue(studentId));
    }

    @Override
    public List<EvaluationDTO> findByProfessionalId(Long professionalId) {
        return mapper.toEvaluationDTOList(
                evaluationRepository.findByProfessionalIdAndIsActiveTrue(professionalId));
    }
}
