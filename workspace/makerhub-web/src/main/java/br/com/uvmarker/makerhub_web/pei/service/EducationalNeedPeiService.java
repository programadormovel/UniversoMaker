
package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeedPei;
import br.com.uvmarker.makerhub_web.pei.dto.EducationalNeedPeiDTO;

public interface EducationalNeedPeiService {

    List<EducationalNeedPei> findAll();

    List<EducationalNeedPeiDTO> findAllActive();

    List<EducationalNeedPeiDTO> findByPeiId(Long peiId);

    EducationalNeedPeiDTO findById(Long id);

    EducationalNeedPeiDTO save(EducationalNeedPeiDTO dto);

    EducationalNeedPeiDTO update(Long id, EducationalNeedPeiDTO dto);

    void inactivateById(Long id);
}
