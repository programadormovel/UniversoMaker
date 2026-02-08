
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionContact;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionContactDTO;

public interface InstitutionContactService {

    List<InstitutionContact> findAll();

    List<InstitutionContactDTO> findAllActive();

    InstitutionContactDTO findById(Long id);

    List<InstitutionContactDTO> findByInstitutionId(Long institutionId);

    InstitutionContactDTO save(InstitutionContactDTO dto);

    InstitutionContactDTO update(Long id, InstitutionContactDTO dto);

    InstitutionContact inactivateById(Long id);
}
