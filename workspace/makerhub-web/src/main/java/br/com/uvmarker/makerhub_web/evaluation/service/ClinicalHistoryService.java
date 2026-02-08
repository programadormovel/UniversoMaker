
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.evaluation.dto.ClinicalHistoryDTO;

public interface ClinicalHistoryService {

    List<ClinicalHistoryDTO> findAll();

    List<ClinicalHistoryDTO> findAllActive();

    List<ClinicalHistoryDTO> findByStudentId(Long studentId);

    List<ClinicalHistoryDTO> findByProfessionalId(Long professionalId);

    ClinicalHistoryDTO findById(Long id);

    ClinicalHistoryDTO save(ClinicalHistoryDTO dto);

    ClinicalHistoryDTO update(Long id, ClinicalHistoryDTO dto);

    void inactivateById(Long id);
}
