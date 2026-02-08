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

import br.com.uvmarker.makerhub_web.person.domain.entity.PersonContact;
import br.com.uvmarker.makerhub_web.person.dto.PersonContactDTO;
import br.com.uvmarker.makerhub_web.person.service.PersonContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/person-contact")
@RequiredArgsConstructor
@Validated
public class PersonContactController {

    private final PersonContactService personContactService;

    @GetMapping
    public ResponseEntity<List<PersonContact>> findAll() {
        return ResponseEntity.ok(personContactService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PersonContactDTO>> findAllActive() {
        return ResponseEntity.ok(personContactService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonContactDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personContactService.findById(id));
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<PersonContactDTO>> findByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok(personContactService.findByPersonId(personId));
    }

    @PostMapping
    public ResponseEntity<PersonContactDTO> save(@Valid @RequestBody PersonContactDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personContactService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonContactDTO> update(@PathVariable Long id, @Valid @RequestBody PersonContactDTO dto) {
        return ResponseEntity.ok(personContactService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<PersonContact> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(personContactService.inactivateById(id));
    }
}
