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

import br.com.uvmarker.makerhub_web.pei.dto.PeiFileDTO;
import br.com.uvmarker.makerhub_web.pei.service.PeiFileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pei-file")
@RequiredArgsConstructor
@Validated
public class PeiFileController {

    private final PeiFileService peiFileService;

    @GetMapping
    public ResponseEntity<List<PeiFileDTO>> findAll() {
        return ResponseEntity.ok(peiFileService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<PeiFileDTO>> findAllActive() {
        return ResponseEntity.ok(peiFileService.findAllActive());
    }

    @GetMapping("/pei/{peiId}")
    public ResponseEntity<List<PeiFileDTO>> findAllActiveByPeiId(@PathVariable Long peiId) {
        return ResponseEntity.ok(peiFileService.findAllActiveByPeiId(peiId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeiFileDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(peiFileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PeiFileDTO> save(@Valid @RequestBody PeiFileDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(peiFileService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeiFileDTO> update(@PathVariable Long id, @Valid @RequestBody PeiFileDTO dto) {
        return ResponseEntity.ok(peiFileService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<Void> inactivateById(@PathVariable Long id) {
        peiFileService.inactivateById(id);
        return ResponseEntity.noContent().build();
    }
}
