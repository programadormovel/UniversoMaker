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
        // Remover usuário existente se houver problema com a senha
        usuarioRepository.findByEmail("admin@universomaker.com").ifPresent(usuario -> {
            if (!passwordEncoder.matches("admin123", usuario.getSenha())) {
                System.out.println("Removendo usuário admin com senha inválida");
                usuarioRepository.delete(usuario);
            }
        });
        
        if (!usuarioRepository.findByEmail("admin@universomaker.com").isPresent()) {
            String senhaEncoded = passwordEncoder.encode("admin123");
            System.out.println("Criando usuário admin com senha hash: " + senhaEncoded);
            
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@universomaker.com");
            admin.setSenha(senhaEncoded);
            admin.setTipoUsuario(TipoUsuario.Administrador);
            admin.setAtivo(true);
            
            usuarioRepository.save(admin);
            System.out.println("Usuário administrador criado: admin@universomaker.com / admin123");
            
            // Teste de validação da senha
            boolean senhaValida = passwordEncoder.matches("admin123", senhaEncoded);
            System.out.println("Teste de validação da senha: " + senhaValida);
        } else {
            System.out.println("Usuário admin já existe e senha é válida");
        }
    }
}