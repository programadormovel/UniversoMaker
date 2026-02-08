package br.com.uvmarker.makerhub_web.professional.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ClassCouncil;

public interface ClassCouncilService {

    List<ClassCouncil> findAll();

    List<ClassCouncil> findAllActive();

    ClassCouncil findById(Long id);

    ClassCouncil save(ClassCouncil classCouncil);

    ClassCouncil inactivateById(Long id);

}
