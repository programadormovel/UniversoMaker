
package br.com.uvmarker.makerhub_web.pei.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.pei.domain.entity.PeiMonitoring;
import br.com.uvmarker.makerhub_web.pei.dto.PeiMonitoringDTO;

public interface PeiMonitoringService {

    List<PeiMonitoring> findAll();

    List<PeiMonitoringDTO> findAllActive();

    List<PeiMonitoringDTO> findByPeiId(Long peiId);

    PeiMonitoringDTO findById(Long id);

    PeiMonitoringDTO save(PeiMonitoringDTO dto);

    PeiMonitoringDTO update(Long id, PeiMonitoringDTO dto);

    void inactivateById(Long id);
}
