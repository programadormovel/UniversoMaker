package br.com.uvmarker.makerhub_web.shared_kernel.controller;

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

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.ContactType;
import br.com.uvmarker.makerhub_web.shared_kernel.service.ContactTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contact-type")
@RequiredArgsConstructor
@Validated
public class ContactTypeController {

    private final ContactTypeService contactTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactType>> findAll() {
        return ResponseEntity.ok(contactTypeService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ContactType>> findAllActive() {
        return ResponseEntity.ok(contactTypeService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contactTypeService.findById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ContactType> save(@Valid @RequestBody ContactType contactType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactTypeService.save(contactType));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ContactType> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(contactTypeService.inactivateById(id));
    }
}
