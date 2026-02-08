package br.com.uvmarker.makerhub_web.evaluation.controller;

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

import br.com.uvmarker.makerhub_web.evaluation.dto.DiagnosticResultDTO;
import br.com.uvmarker.makerhub_web.evaluation.service.DiagnosticResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/diagnostic-result")
@RequiredArgsConstructor
@Validated
public class DiagnosticResultController {

    private final DiagnosticResultService diagnosticResultService;

    @GetMapping
    public ResponseEntity<List<DiagnosticResultDTO>> findAll() {
        return ResponseEntity.ok(diagnosticResultService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<DiagnosticResultDTO>> findAllActive() {
        return ResponseEntity.ok(diagnosticResultService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticResultDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosticResultService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DiagnosticResultDTO> save(@Valid @RequestBody DiagnosticResultDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnosticResultService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticResultDTO> update(@PathVariable Long id,
            @Valid @RequestBody DiagnosticResultDTO dto) {
        return ResponseEntity.ok(diagnosticResultService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        diagnosticResultService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
