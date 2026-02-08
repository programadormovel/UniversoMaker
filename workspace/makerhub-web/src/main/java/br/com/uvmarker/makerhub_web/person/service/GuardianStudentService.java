package br.com.uvmarker.makerhub_web.person.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.person.domain.entity.GuardianStudent;
import br.com.uvmarker.makerhub_web.person.dto.GuardianStudentDTO;

public interface GuardianStudentService {

    List<GuardianStudent> findAll();

    List<GuardianStudentDTO> findAllActive();

    List<GuardianStudentDTO> findByGuardianId(Long guardianId);

    List<GuardianStudentDTO> findByStudentId(Long studentId);

    GuardianStudentDTO findById(Long id);

    GuardianStudentDTO save(GuardianStudentDTO dto);

    GuardianStudentDTO update(Long id, GuardianStudentDTO dto);

    GuardianStudent inactivateById(Long id);

}
