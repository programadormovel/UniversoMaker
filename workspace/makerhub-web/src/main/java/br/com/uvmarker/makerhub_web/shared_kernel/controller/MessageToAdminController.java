package br.com.uvmarker.makerhub_web.shared_kernel.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.MessageToAdmin;
import br.com.uvmarker.makerhub_web.shared_kernel.service.MessageToAdminService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@Validated
public class MessageToAdminController {

    private final MessageToAdminService messageToAdminService;

    @GetMapping("/all")   
    public ResponseEntity<List<MessageToAdmin>> getAllMessages() {
        return ResponseEntity.ok(messageToAdminService.findAll());
    }

    
    @GetMapping("/active")
    public ResponseEntity<List<MessageToAdmin>> findAllActive() {
        return ResponseEntity.ok(messageToAdminService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageToAdmin> findById(@PathVariable Long id) {
        return ResponseEntity.ok(messageToAdminService.findById(id));
    }

    @PostMapping("/send")   
    public ResponseEntity<MessageToAdmin> send(@Valid @RequestBody MessageToAdmin messageToAdmin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageToAdminService.sendMessageToAdmin(messageToAdmin));
    }

    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageToAdmin> inactivateById(@PathVariable Long id) {
        return ResponseEntity.ok(messageToAdminService.inactivateById(id));
    }

}
