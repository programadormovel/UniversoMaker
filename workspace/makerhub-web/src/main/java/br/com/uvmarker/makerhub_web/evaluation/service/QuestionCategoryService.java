
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.evaluation.dto.QuestionCategoryDTO;

public interface QuestionCategoryService {

    List<QuestionCategoryDTO> findAll();

    List<QuestionCategoryDTO> findAllActive();

    QuestionCategoryDTO findById(Long id);

    List<QuestionCategoryDTO> findByTypeId(Long typeId);

    QuestionCategoryDTO save(QuestionCategoryDTO dto);

    QuestionCategoryDTO update(Long id, QuestionCategoryDTO dto);

    void inactivateById(Long id);
}
