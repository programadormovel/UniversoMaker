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

import br.com.uvmarker.makerhub_web.pei.domain.entity.Pei;
import br.com.uvmarker.makerhub_web.pei.dto.PeiDTO;
import br.com.uvmarker.makerhub_web.pei.service.PeiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pei")
@RequiredArgsConstructor
@Validated
public class PeiController {

    private final PeiService peiService;

    @GetMapping
    public ResponseEntity<List<Pei>> findAll() {
        return ResponseEntity.ok(peiService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PeiDTO>> findAllActive() {
        return ResponseEntity.ok(peiService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeiDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(peiService.findById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PeiDTO>> findByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(peiService.findByStudentId(studentId));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<PeiDTO>> findByProfessionalId(@PathVariable Long professionalId) {
        return ResponseEntity.ok(peiService.findByProfessionalId(professionalId));
    }

    @PostMapping
    public ResponseEntity<PeiDTO> save(@Valid @RequestBody PeiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(peiService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeiDTO> update(@PathVariable Long id, @Valid @RequestBody PeiDTO dto) {
        return ResponseEntity.ok(peiService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        peiService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
