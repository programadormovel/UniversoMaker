package br.com.uvmarker.makerhub_web.institution.controller;

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

import br.com.uvmarker.makerhub_web.institution.domain.entity.Institution;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionDTO;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/institution")
@RequiredArgsConstructor
@Validated
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<List<Institution>> findAll() {
        return ResponseEntity.ok(institutionService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<InstitutionDTO>> findAllActive() {
        return ResponseEntity.ok(institutionService.findAllActive());
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<InstitutionDTO>> findByTypeId(@PathVariable Long typeId) {
        return ResponseEntity.ok(institutionService.findByTypeId(typeId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<InstitutionDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(institutionService.findByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InstitutionDTO>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(institutionService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<InstitutionDTO> save(@Valid @RequestBody InstitutionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionDTO> update(@PathVariable Long id, @Valid @RequestBody InstitutionDTO dto) {
        return ResponseEntity.ok(institutionService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Institution> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionService.inactivateById(id));
    }
}
