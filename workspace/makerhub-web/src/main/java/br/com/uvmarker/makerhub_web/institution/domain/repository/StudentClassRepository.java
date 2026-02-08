package br.com.uvmarker.makerhub_web.institution.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.institution.domain.entity.StudentClass;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

    List<StudentClass> findByIsActiveTrue();

    List<StudentClass> findByStudentIdAndIsActiveTrue(Long studentId);

    List<StudentClass> findByInstitutionClass_IdAndIsActiveTrue(Long classId);
}