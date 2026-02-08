package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EvaluationFunctionPei;
import br.com.uvmarker.makerhub_web.pei.dto.EvaluationFunctionPeiDTO;

public interface EvaluationFunctionPeiService {

    List<EvaluationFunctionPei> findAll();

    List<EvaluationFunctionPeiDTO> findAllActive();

    EvaluationFunctionPeiDTO findById(Long id);

    EvaluationFunctionPeiDTO save(EvaluationFunctionPeiDTO dto);

    EvaluationFunctionPeiDTO update(Long id, EvaluationFunctionPeiDTO dto);

    void inactivateById(Long id);
}
