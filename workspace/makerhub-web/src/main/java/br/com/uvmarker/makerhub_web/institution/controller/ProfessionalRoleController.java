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

import br.com.uvmarker.makerhub_web.institution.domain.entity.ProfessionalRole;
import br.com.uvmarker.makerhub_web.institution.service.ProfessionalRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/professional-role")
@RequiredArgsConstructor
@Validated
public class ProfessionalRoleController {

    private final ProfessionalRoleService professionalRoleService;

    @GetMapping("/all")
    public ResponseEntity<List<ProfessionalRole>> findAll() {
        return ResponseEntity.ok(professionalRoleService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProfessionalRole>> findAllActive() {
        return ResponseEntity.ok(professionalRoleService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalRole> findById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalRoleService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProfessionalRole> save(@Valid @RequestBody ProfessionalRole professionalRole) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalRoleService.save(professionalRole));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProfessionalRole> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(professionalRoleService.inactivateById(id));
    }
}
