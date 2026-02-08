package br.com.uvmarker.makerhub_web.shared_kernel.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.ContactType;

public interface ContactTypeService {

    List<ContactType> findAll();

    List<ContactType> findAllActive();

    ContactType findById(Long id);

    ContactType save(ContactType contactType);

    ContactType inactivateById(Long id);
}
