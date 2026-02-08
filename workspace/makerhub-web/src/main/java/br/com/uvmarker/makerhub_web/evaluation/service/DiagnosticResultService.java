
package br.com.uvmarker.makerhub_web.evaluation.service;

import java.util.List;
import br.com.uvmarker.makerhub_web.evaluation.dto.DiagnosticResultDTO;

public interface DiagnosticResultService {

    List<DiagnosticResultDTO> findAll();

    List<DiagnosticResultDTO> findAllActive();

    DiagnosticResultDTO findById(Long id);

    DiagnosticResultDTO save(DiagnosticResultDTO dto);

    DiagnosticResultDTO update(Long id, DiagnosticResultDTO dto);

    void inactivateById(Long id);
}
