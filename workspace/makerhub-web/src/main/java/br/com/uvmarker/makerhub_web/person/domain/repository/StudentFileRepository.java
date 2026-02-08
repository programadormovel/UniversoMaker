package br.com.uvmarker.makerhub_web.person.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.person.domain.entity.StudentFile;


@Repository
public interface StudentFileRepository extends JpaRepository<StudentFile, Long> {

    List<StudentFile> findByIsActiveTrue();

    List<StudentFile> findByStudent_IdAndIsActiveTrue(Long studentId);

}