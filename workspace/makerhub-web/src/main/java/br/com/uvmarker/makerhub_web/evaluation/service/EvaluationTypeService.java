
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationType;

public interface EvaluationTypeService {

    List<EvaluationType> findAll();

    List<EvaluationType> findAllActive();

    EvaluationType findById(Long id);

    EvaluationType save(EvaluationType evaluationType);

    EvaluationType inactivateById(Long id);
}
