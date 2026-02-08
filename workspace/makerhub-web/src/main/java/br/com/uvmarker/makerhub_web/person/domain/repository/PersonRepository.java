package br.com.uvmarker.makerhub_web.person.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.person.domain.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByIsActiveTrue();

    Optional<Person> findByDocument(String document);

    boolean existsByDocument(String document);

    List<Person> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    List<Person> findByType_IdAndIsActiveTrue(Long typeId);

    Optional<Person> findByUserIdAndIsActiveTrue(Long userId);

}