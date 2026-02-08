
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalClass;
import br.com.uvmarker.makerhub_web.institution.dto.ProfessionalClassDTO;

public interface ProfessionalClassService {

    List<ProfessionalClass> findAll();

    List<ProfessionalClassDTO> findAllActive();

    ProfessionalClassDTO findById(Long id);

    List<ProfessionalClassDTO> findByClassId(Long classId);

    List<ProfessionalClassDTO> findByProfessionalId(Long professionalId);

    List<ProfessionalClassDTO> findByProfessionalRoleId(Long professionalRoleId);

    ProfessionalClassDTO save(ProfessionalClassDTO dto);

    ProfessionalClassDTO update(Long id, ProfessionalClassDTO dto);

    ProfessionalClass inactivateById(Long id);
}
