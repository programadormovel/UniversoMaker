package br.com.uvmarker.makerhub_web.institution.controller;

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

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionType;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/institution-type")
@RequiredArgsConstructor
@Validated
public class InstitutionTypeController {

    private final InstitutionTypeService institutionTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<InstitutionType>> findAll() {
        return ResponseEntity.ok(institutionTypeService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<InstitutionType>> findAllActive() {
        return ResponseEntity.ok(institutionTypeService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionTypeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InstitutionType> save(@Valid @RequestBody InstitutionType institutionType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionTypeService.save(institutionType));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<InstitutionType> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionTypeService.inactivateById(id));
    }
}
