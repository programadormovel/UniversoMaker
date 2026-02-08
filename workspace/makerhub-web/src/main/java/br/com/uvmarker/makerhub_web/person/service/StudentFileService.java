
package br.com.uvmarker.makerhub_web.person.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.person.domain.entity.StudentFile;
import br.com.uvmarker.makerhub_web.person.dto.StudentFileDTO;

public interface StudentFileService {

    List<StudentFile> findAll();

    List<StudentFileDTO> findAllActive();

    StudentFileDTO findById(Long id);

    List<StudentFileDTO> findByStudentId(Long studentId);

    StudentFileDTO save(StudentFileDTO studentFileDTO);

    StudentFile inactivateById(Long id);
}
