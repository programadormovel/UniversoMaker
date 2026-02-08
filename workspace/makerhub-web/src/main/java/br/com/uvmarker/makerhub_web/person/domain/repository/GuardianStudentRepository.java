package br.com.uvmarker.makerhub_web.person.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.person.domain.entity.GuardianStudent;

@Repository
public interface GuardianStudentRepository extends JpaRepository<GuardianStudent, Long> {

    List<GuardianStudent> findByIsActiveTrue();

}