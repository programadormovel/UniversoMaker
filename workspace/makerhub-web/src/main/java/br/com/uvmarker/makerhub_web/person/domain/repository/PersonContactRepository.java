package br.com.uvmarker.makerhub_web.person.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.person.domain.entity.PersonContact;

@Repository
public interface PersonContactRepository extends JpaRepository<PersonContact, Long> {

    List<PersonContact> findByIsActiveTrue();

    List<PersonContact> findByPerson_IdAndIsActiveTrue(Long personId);
}