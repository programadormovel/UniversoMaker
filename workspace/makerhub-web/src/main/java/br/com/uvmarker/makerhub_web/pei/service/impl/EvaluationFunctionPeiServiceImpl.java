package br.com.uvmarker.makerhub_web.pei.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EvaluationFunctionPei;
import br.com.uvmarker.makerhub_web.pei.domain.entity.FunctionType;
import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;
import br.com.uvmarker.makerhub_web.pei.domain.repository.EvaluationFunctionPeiRepository;
import br.com.uvmarker.makerhub_web.pei.domain.repository.FunctionTypeRepository;
import br.com.uvmarker.makerhub_web.pei.domain.repository.PeiRepository;
import br.com.uvmarker.makerhub_web.pei.dto.EvaluationFunctionPeiDTO;
import br.com.uvmarker.makerhub_web.pei.mapper.CentralMapperPei;
import br.com.uvmarker.makerhub_web.pei.service.EvaluationFunctionPeiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationFunctionPeiServiceImpl implements EvaluationFunctionPeiService {

    private final EvaluationFunctionPeiRepository evaluationFunctionPeiRepository;
    private final CentralMapperPei mapper;
    private final FunctionTypeRepository functionTypeRepository;
    private final PeiRepository peiRepository;

    @Override
    public List<EvaluationFunctionPei> findAll() {
        return evaluationFunctionPeiRepository.findAll(); 
    }

    @Override
    public List<EvaluationFunctionPeiDTO> findAllActive() {
        return mapper.toEvaluationFunctionPeiDTOList(evaluationFunctionPeiRepository.findByIsActiveTrue());
    }

    @Override
    public EvaluationFunctionPeiDTO findById(Long id) {
        EvaluationFunctionPei evaluationFunctionPei = evaluationFunctionPeiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta de avaliação não encontrada com ID: " + id));
        return mapper.toEvaluationFunctionPeiDTO(evaluationFunctionPei);
    }

    @Override
    public EvaluationFunctionPeiDTO save(EvaluationFunctionPeiDTO dto) {
        Pei pei = peiRepository.findById(dto.getPeiId())
                .orElseThrow(
                        () -> new EntityNotFoundException("PEI não encontrado com ID: " + dto.getPeiId()));

        FunctionType function = functionTypeRepository.findById(dto.getFunctionId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Função não encontrada com ID: " + dto.getFunctionId()));

        EvaluationFunctionPei entity = mapper.toEvaluationFunctionPei(dto);
        entity.setPei(pei);
        entity.setFunction(function);

        EvaluationFunctionPei saved = evaluationFunctionPeiRepository.save(entity);
        return mapper.toEvaluationFunctionPeiDTO(saved);
    }

    @Override
    public EvaluationFunctionPeiDTO update(Long id, EvaluationFunctionPeiDTO dto) {
        EvaluationFunctionPei existing = evaluationFunctionPeiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta de avaliação não encontrada com ID: " + id));

        Pei pei = peiRepository.findById(dto.getPeiId())
                .orElseThrow(
                        () -> new EntityNotFoundException("PEI não encontrado com ID: " + dto.getPeiId()));

        FunctionType function = functionTypeRepository.findById(dto.getFunctionId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Função não encontrada com ID: " + dto.getFunctionId()));

        existing.setPei(pei);
        existing.setFunction(function);
        existing.setNotes(dto.getNotes());
        existing.setScore(dto.getScore());

        EvaluationFunctionPei updated = evaluationFunctionPeiRepository.save(existing);
        return mapper.toEvaluationFunctionPeiDTO(updated);
    }

    @Override
    public void inactivateById(Long id) {
        EvaluationFunctionPei evaluationFunctionPei = evaluationFunctionPeiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta de avaliação não encontrada com ID: " + id));
        evaluationFunctionPei.setIsActive(false);
        evaluationFunctionPeiRepository.save(evaluationFunctionPei);
    }

  
}
