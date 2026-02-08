package br.com.uvmarker.makerhub_web.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.common.enums.StatusUser;
import br.com.uvmarker.makerhub_web.user.domain.entity.Role;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.repository.RoleRepository;
import br.com.uvmarker.makerhub_web.user.domain.repository.UserRepository;
import br.com.uvmarker.makerhub_web.user.dto.UserDTO;
import br.com.uvmarker.makerhub_web.user.dto.UserRegisterDTO;
import br.com.uvmarker.makerhub_web.user.dto.UserSummaryDTO;
import br.com.uvmarker.makerhub_web.user.mapper.CentralMapperUser;
import br.com.uvmarker.makerhub_web.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CentralMapperUser userMapper;

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toUserDTOList(userRepository.findAll());
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(userMapper::toUserDTO)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public UserDTO registerUser(UserRegisterDTO dto) {
        // Verifica se o e-mail já está em uso
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        // Busca a role padrão
        Role defaultRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Role padrão não encontrada"));

        // Cria o usuário
        User user = User.builder()
                .fullname(dto.getFullname())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .statusUser(StatusUser.ACTIVE)
                .roles(Set.of(defaultRole))
                .build();

        return userMapper.toUserDTO(userRepository.save(user));
    }

    @SuppressWarnings("null")
    @Override
    public UserDTO inctivateById(Long id) {
        return userRepository.findById(id).map(user -> {
            user.setIsActive(false);
            user.setStatusUser(StatusUser.INACTIVE);

            return userMapper.toUserDTO(userRepository.save(user));
        }).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }

    @SuppressWarnings("null")
    @Override
    public UserDTO update(Long id, UserDTO dto) {
        return userRepository.findById(id).map(user -> {
            user.setFullname(dto.getFullname());
            user.setProfileImageUrl(dto.getProfileImageUrl());

            return userMapper.toUserDTO(userRepository.save(user));
        }).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }

    @SuppressWarnings("null")
    @Override
    public void changePassword(Long id, String newPassword) {
        userRepository.findById(id).map(user -> {
            user.setPassword(passwordEncoder.encode(newPassword));

            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }

    @Override
    public void addRoles(Long id, Set<Role> newRoles) {
        userRepository.findById(id).map(user -> {
            user.getRoles().addAll(newRoles);
   
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }


    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }

    @Override
    public Optional<UserSummaryDTO> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserSummaryDTO);
    }
}
