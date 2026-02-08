
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.EvaluationItem;

public interface EvaluationItemService {

    List<EvaluationItem> findAll();

    List<EvaluationItem> findAllActive();

    EvaluationItem findById(Long id);

    EvaluationItem save(EvaluationItem evaluationItem);

    EvaluationItem inactivateById(Long id);
}
