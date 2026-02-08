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

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalClass;
import br.com.uvmarker.makerhub_web.institution.dto.ProfessionalClassDTO;
import br.com.uvmarker.makerhub_web.institution.service.ProfessionalClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/professional-class")
@RequiredArgsConstructor
@Validated
public class ProfessionalClassController {

    private final ProfessionalClassService professionalClassService;

    @GetMapping
    public ResponseEntity<List<ProfessionalClass>> findAll() {
        return ResponseEntity.ok(professionalClassService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProfessionalClassDTO>> findAllActive() {
        return ResponseEntity.ok(professionalClassService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalClassDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalClassService.findById(id));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<ProfessionalClassDTO>> findByClassId(@PathVariable Long classId) {
        return ResponseEntity.ok(professionalClassService.findByClassId(classId));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<ProfessionalClassDTO>> findByProfessionalId(@PathVariable Long professionalId) {
        return ResponseEntity.ok(professionalClassService.findByProfessionalId(professionalId));
    }

    @GetMapping("/role/{professionalRoleId}")
    public ResponseEntity<List<ProfessionalClassDTO>> findByProfessionalRoleId(@PathVariable Long professionalRoleId) {
        return ResponseEntity.ok(professionalClassService.findByProfessionalRoleId(professionalRoleId));
    }

    @PostMapping
    public ResponseEntity<ProfessionalClassDTO> save(@Valid @RequestBody ProfessionalClassDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalClassService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalClassDTO> update(@PathVariable Long id,
            @Valid @RequestBody ProfessionalClassDTO dto) {
        return ResponseEntity.ok(professionalClassService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<ProfessionalClass> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalClassService.inactivateById(id));
    }
}
