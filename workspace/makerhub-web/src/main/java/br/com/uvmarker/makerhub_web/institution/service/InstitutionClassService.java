
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionClass;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionClassDTO;

public interface InstitutionClassService {

    List<InstitutionClass> findAll();

    List<InstitutionClassDTO> findAllActive();

    InstitutionClassDTO findById(Long id);

    List<InstitutionClassDTO> findByInstitutionId(Long institutionId);

    InstitutionClassDTO save(InstitutionClassDTO dto);

    InstitutionClassDTO update(Long id, InstitutionClassDTO dto);

    InstitutionClass inactivateById(Long id);
}
