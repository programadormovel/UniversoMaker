
package br.com.uvmarker.makerhub_web.professional.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.professional.domain.entity.Specialty;
import br.com.uvmarker.makerhub_web.professional.dto.SpecialtyDTO;

public interface SpecialtyService {

    List<Specialty> findAll();

    List<SpecialtyDTO> findAllActive();

    SpecialtyDTO findById(Long id);

    SpecialtyDTO findByCouncilId(Long councilId);

    SpecialtyDTO save(SpecialtyDTO specialtyDTO);

    Specialty inactivateById(Long id);
}
