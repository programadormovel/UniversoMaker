package br.com.uvmarker.makerhub_web.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.user.domain.entity.Role;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.repository.RoleRepository;
import br.com.uvmarker.makerhub_web.user.domain.repository.UserRepository;
import br.com.uvmarker.makerhub_web.user.dto.RoleDTO;
import br.com.uvmarker.makerhub_web.user.mapper.CentralMapperUser;
import br.com.uvmarker.makerhub_web.user.service.RoleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CentralMapperUser mapper;

    @Override
    public List<RoleDTO> findAll() {
        return mapper.toRoleDTOList(roleRepository.findAll());
    }

    @Override
    public RoleDTO createRole(RoleDTO dto) {
        Role role = mapper.toRole(dto);
        Role saved = roleRepository.save(role);
        return mapper.toRoleDTO(saved);
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void removeRoleFromUsuario(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        user.getRoles().remove(role);
        userRepository.save(user);
    }

}
