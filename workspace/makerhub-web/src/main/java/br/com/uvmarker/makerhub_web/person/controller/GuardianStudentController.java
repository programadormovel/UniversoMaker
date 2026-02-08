package br.com.uvmarker.makerhub_web.person.controller;

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

import br.com.uvmarker.makerhub_web.person.domain.entity.GuardianStudent;
import br.com.uvmarker.makerhub_web.person.dto.GuardianStudentDTO;
import br.com.uvmarker.makerhub_web.person.service.GuardianStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/guardian-student")
@RequiredArgsConstructor
@Validated
public class GuardianStudentController {

    private final GuardianStudentService guardianStudentService;

    @GetMapping
    public ResponseEntity<List<GuardianStudent>> findAll() {
        return ResponseEntity.ok(guardianStudentService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<GuardianStudentDTO>> findAllActive() {
        return ResponseEntity.ok(guardianStudentService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardianStudentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(guardianStudentService.findById(id));
    }

    @GetMapping("/guardian/{guardianId}")
    public ResponseEntity<List<GuardianStudentDTO>> findByGuardianId(@PathVariable Long guardianId) {
        return ResponseEntity.ok(guardianStudentService.findByGuardianId(guardianId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GuardianStudentDTO>> findByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(guardianStudentService.findByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<GuardianStudentDTO> save(@Valid @RequestBody GuardianStudentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guardianStudentService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuardianStudentDTO> update(@PathVariable Long id,
            @Valid @RequestBody GuardianStudentDTO dto) {
        return ResponseEntity.ok(guardianStudentService.update(id, dto));
    }

    @PutMapping("/inactivate/{id}")
    public ResponseEntity<GuardianStudent> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(guardianStudentService.inactivateById(id));
    }
}
