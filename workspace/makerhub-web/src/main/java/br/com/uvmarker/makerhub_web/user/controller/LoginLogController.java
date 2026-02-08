package br.com.uvmarker.makerhub_web.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.user.domain.entity.LoginLog;
import br.com.uvmarker.makerhub_web.user.service.LoginLogService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/login-log")
@RequiredArgsConstructor
@Validated
public class LoginLogController {

    private final LoginLogService loginLogService;

    @GetMapping
    public ResponseEntity<List<LoginLog>> findAll() {
        return ResponseEntity.ok(loginLogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginLog> findById(@PathVariable Long id) {
        return loginLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        loginLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
