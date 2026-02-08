
package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeed;

public interface EducationalNeedService {

    List<EducationalNeed> findAll();

    List<EducationalNeed> findAllActive();

    EducationalNeed findById(Long id);

    EducationalNeed save(EducationalNeed educationalNeed);

    EducationalNeed inactivateById(Long id);
}
