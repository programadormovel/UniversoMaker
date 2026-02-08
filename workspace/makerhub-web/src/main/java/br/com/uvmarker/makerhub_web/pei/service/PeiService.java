
package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;
import br.com.uvmarker.makerhub_web.pei.dto.PeiDTO;

public interface PeiService {

    List<Pei> findAll();

    List<PeiDTO> findAllActive();

    List<PeiDTO> findByStudentId(Long studentId);

    List<PeiDTO> findByProfessionalId(Long professionalId);

    PeiDTO findById(Long id);

    PeiDTO save(PeiDTO dto);

    PeiDTO update(Long id, PeiDTO dto);

    void inactivateById(Long id);
}
