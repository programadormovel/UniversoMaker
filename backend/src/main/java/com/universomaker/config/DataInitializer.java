package com.universomaker.config;

import com.universomaker.entity.Usuario;
import com.universomaker.entity.TipoUsuario;
import com.universomaker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!usuarioRepository.findByEmail("admin@universomaker.com").isPresent()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@universomaker.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setTipoUsuario(TipoUsuario.Administrador);
            admin.setAtivo(true);
            
            usuarioRepository.save(admin);
            System.out.println("Usuário administrador criado: admin@universomaker.com / admin123");
        }
    }
}