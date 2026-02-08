package br.com.uvmarker.makerhub_web.pei.controller;

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

import br.com.uvmarker.makerhub_web.pei.domain.entity.EducationalNeed;
import br.com.uvmarker.makerhub_web.pei.service.EducationalNeedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/educational-need")
@RequiredArgsConstructor
@Validated
public class EducationalNeedController {

    private final EducationalNeedService educationalNeedService;

    @GetMapping("/all")
    public ResponseEntity<List<EducationalNeed>> findAll() {
        return ResponseEntity.ok(educationalNeedService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EducationalNeed>> findAllActive() {
        return ResponseEntity.ok(educationalNeedService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationalNeed> findById(@PathVariable Long id) {
        return ResponseEntity.ok(educationalNeedService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EducationalNeed> save(@Valid @RequestBody EducationalNeed educationalNeed) {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationalNeedService.save(educationalNeed));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EducationalNeed> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(educationalNeedService.inactivateById(id));
    }
}
