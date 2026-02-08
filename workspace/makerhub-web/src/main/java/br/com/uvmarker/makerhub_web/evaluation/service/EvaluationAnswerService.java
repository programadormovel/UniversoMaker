package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationAnswerDTO;

public interface EvaluationAnswerService {

    List<EvaluationAnswerDTO> findAll();

    List<EvaluationAnswerDTO> findAllActive();

    EvaluationAnswerDTO findById(Long id);

    EvaluationAnswerDTO save(EvaluationAnswerDTO dto);

    EvaluationAnswerDTO update(Long id, EvaluationAnswerDTO dto);

    void inactivateById(Long id);
}
