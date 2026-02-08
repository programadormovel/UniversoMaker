package br.com.uvmarker.makerhub_web.professional.controller;

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

import br.com.uvmarker.makerhub_web.professional.domain.entity.ProfessionalSpecialty;
import br.com.uvmarker.makerhub_web.professional.dto.ProfessionalSpecialtyDTO;
import br.com.uvmarker.makerhub_web.professional.service.ProfessionalSpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/professional-specialty")
@RequiredArgsConstructor
@Validated
public class ProfessionalSpecialtyController {

    private final ProfessionalSpecialtyService professionalSpecialtyService;

    @GetMapping
    public ResponseEntity<List<ProfessionalSpecialty>> findAll() {
        return ResponseEntity.ok(professionalSpecialtyService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProfessionalSpecialtyDTO>> findAllActive() {
        return ResponseEntity.ok(professionalSpecialtyService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalSpecialtyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalSpecialtyService.findById(id));
    }

    @GetMapping("/document/{documentNr}")
    public ResponseEntity<ProfessionalSpecialtyDTO> findByDocumentNr(@PathVariable String documentNr) {
        return ResponseEntity.ok(professionalSpecialtyService.findByDocumentNr(documentNr));
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<ProfessionalSpecialtyDTO>> findByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok(professionalSpecialtyService.findByPersonId(personId));
    }

    @GetMapping("/specialty/{specialtyId}")
    public ResponseEntity<List<ProfessionalSpecialtyDTO>> findBySpecialtyId(@PathVariable Long specialtyId) {
        return ResponseEntity.ok(professionalSpecialtyService.findBySpecialtyId(specialtyId));
    }

    @PostMapping
    public ResponseEntity<ProfessionalSpecialtyDTO> save(@Valid @RequestBody ProfessionalSpecialtyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalSpecialtyService.save(dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<ProfessionalSpecialty> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalSpecialtyService.inactivateById(id));
    }
}
