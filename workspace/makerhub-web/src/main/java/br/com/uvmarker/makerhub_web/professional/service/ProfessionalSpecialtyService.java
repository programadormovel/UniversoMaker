
package br.com.uvmarker.makerhub_web.professional.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ProfessionalSpecialty;
import br.com.uvmarker.makerhub_web.professional.dto.ProfessionalSpecialtyDTO;


public interface ProfessionalSpecialtyService {

    List<ProfessionalSpecialty> findAll();
    
    List<ProfessionalSpecialtyDTO> findAllActive();

    ProfessionalSpecialtyDTO findById(Long id);

    ProfessionalSpecialtyDTO findByDocumentNr(String documentNr);

    List<ProfessionalSpecialtyDTO> findByPersonId(Long personId);

    List<ProfessionalSpecialtyDTO> findBySpecialtyId(Long specialtyId);

    ProfessionalSpecialtyDTO save(ProfessionalSpecialtyDTO professionalSpecialtyDTO);

    ProfessionalSpecialty inactivateById(Long id);
}
