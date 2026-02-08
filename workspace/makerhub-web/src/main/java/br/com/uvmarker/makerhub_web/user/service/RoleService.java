
package br.com.uvmarker.makerhub_web.user.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.user.dto.RoleDTO;

public interface RoleService {

    List<RoleDTO> findAll();
    RoleDTO createRole(RoleDTO dto);
    void addRoleToUser(Long userId, String roleName);
    void removeRoleFromUsuario(Long userId, String roleName);

}
