package br.com.uvmarker.makerhub_web.person.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.person.domain.entity.Person;
import br.com.uvmarker.makerhub_web.person.dto.PersonDTO;
import br.com.uvmarker.makerhub_web.person.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
@Validated
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PersonDTO>> findAllActive() {
        return ResponseEntity.ok(personService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<PersonDTO>> findByTypeId(@PathVariable Long typeId) {
        return ResponseEntity.ok(personService.findByTypeId(typeId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PersonDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.findByName(name));
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<PersonDTO> findByDocument(@PathVariable String document) {
        return ResponseEntity.ok(personService.findByDocument(document));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PersonDTO> findByUserId(@PathVariable Long userId) {
        return personService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> save(@Valid @RequestBody PersonDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @Valid @RequestBody PersonDTO dto) {
        return ResponseEntity.ok(personService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Person> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.inactivateById(id));
    }
}
