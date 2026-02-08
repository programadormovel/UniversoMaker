
package br.com.uvmarker.makerhub_web.user.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.uvmarker.makerhub_web.user.domain.entity.Role;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.dto.UserDTO;
import br.com.uvmarker.makerhub_web.user.dto.UserRegisterDTO;
import br.com.uvmarker.makerhub_web.user.dto.UserSummaryDTO;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    Optional<User> findByUsername(String username);

    UserDTO registerUser(UserRegisterDTO registerUserDTO);

    UserDTO inctivateById(Long id);

    UserDTO update(Long id, UserDTO dto);

    void changePassword(Long id, String newPassword);

    void addRoles(Long id, Set<Role> newRoles);

    Optional<UserSummaryDTO> findUserById(Long id); // troca de dados entre domínios
    
    boolean existsByUsername(String username); // troca de dados entre domínios
}
