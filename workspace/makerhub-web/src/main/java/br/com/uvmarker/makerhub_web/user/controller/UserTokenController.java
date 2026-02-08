package br.com.uvmarker.makerhub_web.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uvmarker.makerhub_web.user.domain.entity.UserToken;
import br.com.uvmarker.makerhub_web.user.service.UserTokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user-token")
@RequiredArgsConstructor
@Validated
public class UserTokenController {

    private final UserTokenService userTokenService;

    @GetMapping("/validate")
    public ResponseEntity<Boolean> isRefreshTokenValid(@RequestParam String token) {
        return ResponseEntity.ok(userTokenService.isRefreshTokenValid(token));
    }

    @PostMapping("/revoke")
    public ResponseEntity<Void> revokeToken(@RequestParam String token) {
        userTokenService.revokeToken(token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{token}")
    public ResponseEntity<UserToken> getByToken(@PathVariable String token) {
        return userTokenService.getByToken(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
