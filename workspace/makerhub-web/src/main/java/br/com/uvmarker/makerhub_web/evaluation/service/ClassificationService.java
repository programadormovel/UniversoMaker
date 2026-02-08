
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Classification;

public interface ClassificationService {

    List<Classification> findAll();

    List<Classification> findAllActive();

    Classification findById(Long id);

    Classification save(Classification classification);

    Classification inactivateById(Long id);

}
