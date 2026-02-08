
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationQuestionDTO;

public interface EvaluationQuestionService {

    List<EvaluationQuestionDTO> findAll();

    List<EvaluationQuestionDTO> findAllActive();

    EvaluationQuestionDTO findById(Long id);

    EvaluationQuestionDTO save(EvaluationQuestionDTO dto);

    EvaluationQuestionDTO update(Long id, EvaluationQuestionDTO dto);

    void inactivateById(Long id);
}
