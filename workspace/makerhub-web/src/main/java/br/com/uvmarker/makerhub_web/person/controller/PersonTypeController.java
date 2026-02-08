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

import br.com.uvmarker.makerhub_web.person.domain.entity.PersonType;
import br.com.uvmarker.makerhub_web.person.service.PersonTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/person-type")
@RequiredArgsConstructor
@Validated
public class PersonTypeController {

    private final PersonTypeService personTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonType>> findAll() {
        return ResponseEntity.ok(personTypeService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PersonType>> findAllActive() {
        return ResponseEntity.ok(personTypeService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personTypeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PersonType> save(@Valid @RequestBody PersonType personType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personTypeService.save(personType));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PersonType> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(personTypeService.inactivateById(id));
    }
}
