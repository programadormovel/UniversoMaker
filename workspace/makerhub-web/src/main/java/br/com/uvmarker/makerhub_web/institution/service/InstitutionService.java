
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.Institution;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionDTO;

public interface InstitutionService {

    List<Institution> findAll();

    List<InstitutionDTO> findAllActive();

    List<InstitutionDTO> findByTypeId(Long typeId);

    List<InstitutionDTO> findByName(String name);

    InstitutionDTO findById(Long id);

    List<InstitutionDTO> findByUserId(Long userId);

    InstitutionDTO save(InstitutionDTO dto);

    InstitutionDTO update(Long id, InstitutionDTO dto);

    Institution inactivateById(Long id);
}
