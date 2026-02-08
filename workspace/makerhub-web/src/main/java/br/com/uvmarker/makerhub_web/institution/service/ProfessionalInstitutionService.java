
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalInstitution;
import br.com.uvmarker.makerhub_web.institution.dto.ProfessionalInstitutionDTO;

public interface ProfessionalInstitutionService {

    List<ProfessionalInstitution> findAll();

    List<ProfessionalInstitutionDTO> findAllActive();

    ProfessionalInstitutionDTO findById(Long id);

    List<ProfessionalInstitutionDTO> findByInstitutionId(Long institutionId);

    List<ProfessionalInstitutionDTO> findByProfessionalId(Long professionalId);

    List<ProfessionalInstitutionDTO> findByProfessionalRoleId(Long professionalRoleId);

    ProfessionalInstitutionDTO save(ProfessionalInstitutionDTO dto);

    ProfessionalInstitutionDTO update(Long id, ProfessionalInstitutionDTO dto);

    ProfessionalInstitution inactivateById(Long id);
}
