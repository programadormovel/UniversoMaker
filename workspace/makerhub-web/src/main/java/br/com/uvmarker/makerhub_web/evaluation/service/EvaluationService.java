
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.evaluation.dto.EvaluationDTO;

public interface EvaluationService {

    List<EvaluationDTO> findAll();

    List<EvaluationDTO> findAllActive();

    List<EvaluationDTO> findByStudentId(Long studentId);

    List<EvaluationDTO> findByProfessionalId(Long professionalId);

    EvaluationDTO findById(Long id);

    EvaluationDTO save(EvaluationDTO dto);

    EvaluationDTO update(Long id, EvaluationDTO dto);

    void inactivateById(Long id);
}
