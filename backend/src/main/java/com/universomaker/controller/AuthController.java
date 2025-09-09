package com.universomaker.controller;

import com.universomaker.dto.LoginRequest;
import com.universomaker.dto.LoginResponse;
import com.universomaker.entity.Usuario;
import com.universomaker.security.JwtUtils;
import com.universomaker.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("=== TENTATIVA DE LOGIN ===");
            System.out.println("Email: " + loginRequest.getEmail());
            System.out.println("Senha: " + loginRequest.getSenha());
            
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
            );
            
            System.out.println("Autenticação bem-sucedida!");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            Usuario usuario = (Usuario) authentication.getPrincipal();
            
            return ResponseEntity.ok(new LoginResponse(
                jwt,
                usuario.getNome(),
                usuario.getTipoUsuario().toString()
            ));
        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }
    }
}