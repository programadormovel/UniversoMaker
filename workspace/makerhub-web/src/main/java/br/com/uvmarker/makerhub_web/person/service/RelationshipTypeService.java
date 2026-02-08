
package br.com.uvmarker.makerhub_web.person.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.person.domain.entity.RelationshipType;

public interface RelationshipTypeService {

    List<RelationshipType> findAll();

    List<RelationshipType> findAllActive();

    RelationshipType findById(Long id);

    RelationshipType save(RelationshipType relationshipType);

    RelationshipType inactivateById(Long id);
}
