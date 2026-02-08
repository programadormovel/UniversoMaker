package br.com.uvmarker.makerhub_web.common.custom.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.common.custom.service.impl.CustomUserDetailsImpl;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Carrega o usuário com roles já inicializadas
        User user = userRepository.findByUsernameWithRoles(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Constrói o UserDetails com os dados prontos
        return new CustomUserDetailsImpl(user);
    }

}