
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionType;


public interface InstitutionTypeService {

    List<InstitutionType> findAll();

    List<InstitutionType> findAllActive();

    InstitutionType findById(Long id);

    InstitutionType save(InstitutionType institutionType);

    InstitutionType inactivateById(Long id);
}
