package br.com.uvmarker.makerhub_web.person.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.person.domain.entity.RelationshipType;

@Repository
public interface RelationshipTypeRepository extends JpaRepository<RelationshipType, Long> {

    List<RelationshipType> findByIsActiveTrue();
    
}