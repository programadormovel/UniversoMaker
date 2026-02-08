
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationFileDTO;

public interface EvaluationFileService {

    List<EvaluationFileDTO> findAll();

    List<EvaluationFileDTO> findAllActive();

    List<EvaluationFileDTO> findAllActiveByEvaluationId(Long evaluationId);

    EvaluationFileDTO findById(Long id);

    EvaluationFileDTO save(EvaluationFileDTO dto);

    EvaluationFileDTO update(Long id, EvaluationFileDTO dto);

    void inactivateById(Long id);
}
