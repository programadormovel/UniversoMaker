
package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.pei.dto.PeiFileDTO;

public interface PeiFileService {

    List<PeiFileDTO> findAll();

    List<PeiFileDTO> findAllActive();

    List<PeiFileDTO> findAllActiveByPeiId(Long peiId);

    PeiFileDTO findById(Long id);

    PeiFileDTO save(PeiFileDTO dto);

    PeiFileDTO update(Long id, PeiFileDTO dto);

    void inactivateById(Long id);
}
