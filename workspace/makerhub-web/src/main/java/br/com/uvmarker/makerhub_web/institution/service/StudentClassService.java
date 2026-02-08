
package br.com.uvmarker.makerhub_web.institution.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.institution.domain.entity.StudentClass;
import br.com.uvmarker.makerhub_web.institution.dto.StudentClassDTO;

public interface StudentClassService {

    List<StudentClass> findAll();

    List<StudentClassDTO> findAllActive();

    StudentClassDTO findById(Long id);

    List<StudentClassDTO> findByClassId(Long classId);

    List<StudentClassDTO> findByStudentId(Long studentId);

    StudentClassDTO save(StudentClassDTO dto);

    StudentClassDTO update(Long id, StudentClassDTO dto);

    StudentClass inactivateById(Long id);
}
