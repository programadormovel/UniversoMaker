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

import br.com.uvmarker.makerhub_web.institution.domain.entity.InstitutionContact;
import br.com.uvmarker.makerhub_web.institution.dto.InstitutionContactDTO;
import br.com.uvmarker.makerhub_web.institution.service.InstitutionContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/institution-contact")
@RequiredArgsConstructor
@Validated
public class InstitutionContactController {

    private final InstitutionContactService institutionContactService;

    @GetMapping
    public ResponseEntity<List<InstitutionContact>> findAll() {
        return ResponseEntity.ok(institutionContactService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<InstitutionContactDTO>> findAllActive() {
        return ResponseEntity.ok(institutionContactService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionContactDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionContactService.findById(id));
    }

    @GetMapping("/institution/{institutionId}")
    public ResponseEntity<List<InstitutionContactDTO>> findByInstitutionId(@PathVariable Long institutionId) {
        return ResponseEntity.ok(institutionContactService.findByInstitutionId(institutionId));
    }

    @PostMapping
    public ResponseEntity<InstitutionContactDTO> save(@Valid @RequestBody InstitutionContactDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(institutionContactService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionContactDTO> update(@PathVariable Long id,
            @Valid @RequestBody InstitutionContactDTO dto) {
        return ResponseEntity.ok(institutionContactService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<InstitutionContact> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionContactService.inactivateById(id));
    }
}
