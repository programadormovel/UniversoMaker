package br.com.uvmarker.makerhub_web.pei.controller;

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

import br.com.uvmarker.makerhub_web.pei.domain.entity.PeiMonitoring;
import br.com.uvmarker.makerhub_web.pei.dto.PeiMonitoringDTO;
import br.com.uvmarker.makerhub_web.pei.service.PeiMonitoringService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pei-monitoring")
@RequiredArgsConstructor
@Validated
public class PeiMonitoringController {

    private final PeiMonitoringService peiMonitoringService;

    @GetMapping
    public ResponseEntity<List<PeiMonitoring>> findAll() {
        return ResponseEntity.ok(peiMonitoringService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PeiMonitoringDTO>> findAllActive() {
        return ResponseEntity.ok(peiMonitoringService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeiMonitoringDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(peiMonitoringService.findById(id));
    }

    @GetMapping("/pei/{peiId}")
    public ResponseEntity<List<PeiMonitoringDTO>> findByPeiId(@PathVariable Long peiId) {
        return ResponseEntity.ok(peiMonitoringService.findByPeiId(peiId));
    }

    @PostMapping
    public ResponseEntity<PeiMonitoringDTO> save(@Valid @RequestBody PeiMonitoringDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(peiMonitoringService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeiMonitoringDTO> update(@PathVariable Long id, @Valid @RequestBody PeiMonitoringDTO dto) {
        return ResponseEntity.ok(peiMonitoringService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        peiMonitoringService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
