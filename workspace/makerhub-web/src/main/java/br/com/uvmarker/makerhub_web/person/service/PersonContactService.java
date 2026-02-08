
package br.com.uvmarker.makerhub_web.person.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.person.domain.entity.PersonContact;
import br.com.uvmarker.makerhub_web.person.dto.PersonContactDTO;

public interface PersonContactService {

    List<PersonContact> findAll();

    List<PersonContactDTO> findAllActive();

    PersonContactDTO findById(Long id);

    List<PersonContactDTO> findByPersonId(Long personId);

    PersonContactDTO save(PersonContactDTO dto);

    PersonContactDTO update(Long id, PersonContactDTO dto);

    PersonContact inactivateById(Long id);

}
