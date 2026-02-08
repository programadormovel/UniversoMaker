
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalRole;

public interface ProfessionalRoleService {

    List<ProfessionalRole> findAll();

    List<ProfessionalRole> findAllActive();

    ProfessionalRole findById(Long id);

    ProfessionalRole save(ProfessionalRole professionalRole);

    ProfessionalRole inactivateById(Long id);
}
