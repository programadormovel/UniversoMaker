package br.com.uvmarker.makerhub_web.evaluation.controller;

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

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.Classification;
import br.com.uvmarker.makerhub_web.evaluation.service.ClassificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/classification")
@RequiredArgsConstructor
@Validated
public class ClassificationController {

    private final ClassificationService classificationService;

    @GetMapping("/all")
    public ResponseEntity<List<Classification>> findAll() {
        return ResponseEntity.ok(classificationService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Classification>> findAllActive() {
        return ResponseEntity.ok(classificationService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classification> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classificationService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Classification> save(@Valid @RequestBody Classification classification) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classificationService.save(classification));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Classification> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(classificationService.inactivateById(id));
    }
}
