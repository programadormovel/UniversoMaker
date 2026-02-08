package br.com.uvmarker.makerhub_web.professional.controller;

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

import br.com.uvmarker.makerhub_web.professional.domain.entity.ClassCouncil;
import br.com.uvmarker.makerhub_web.professional.service.ClassCouncilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/class-council")
@RequiredArgsConstructor
@Validated
public class ClassCouncilController {

    private final ClassCouncilService classCouncilService;

    @GetMapping("/all")
    public ResponseEntity<List<ClassCouncil>> findAll() {
        return ResponseEntity.ok(classCouncilService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ClassCouncil>> findAllActive() {
        return ResponseEntity.ok(classCouncilService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassCouncil> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classCouncilService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassCouncil> save(@Valid @RequestBody ClassCouncil classCouncil) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classCouncilService.save(classCouncil));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClassCouncil> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(classCouncilService.inactivateById(id));
    }
}
