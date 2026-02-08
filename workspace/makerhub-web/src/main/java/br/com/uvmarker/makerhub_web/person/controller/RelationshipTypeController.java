package br.com.uvmarker.makerhub_web.person.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.person.domain.entity.RelationshipType;
import br.com.uvmarker.makerhub_web.person.service.RelationshipTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/relationship-type")
@RequiredArgsConstructor
@Validated
public class RelationshipTypeController {

    private final RelationshipTypeService relationshipTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<RelationshipType>> findAll() {
        return ResponseEntity.ok(relationshipTypeService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<RelationshipType>> findAllActive() {
        return ResponseEntity.ok(relationshipTypeService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelationshipType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(relationshipTypeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RelationshipType> save(@Valid @RequestBody RelationshipType relationshipType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(relationshipTypeService.save(relationshipType));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RelationshipType> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(relationshipTypeService.inactivateById(id));
    }
}

