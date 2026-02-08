
package br.com.uvmarker.makerhub_web.person.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.person.domain.entity.PersonType;

public interface PersonTypeService {

    List<PersonType> findAll();

    List<PersonType> findAllActive();

    PersonType findById(Long id);

    PersonType save(PersonType personType);

    PersonType inactivateById(Long id);

}
