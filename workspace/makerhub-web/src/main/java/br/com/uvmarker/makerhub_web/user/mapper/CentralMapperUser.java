package br.com.uvmarker.makerhub_web.user.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.uvmarker.makerhub_web.user.domain.entity.Role;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.entity.UserToken;
import br.com.uvmarker.makerhub_web.user.dto.*;

@Mapper(componentModel = "spring")
public interface CentralMapperUser {

    CentralMapperUser INSTANCE = Mappers.getMapper(CentralMapperUser.class);

    // ===================== USER =====================
    @Mappings({
        @Mapping(source = "roles", target = "roleNames", qualifiedByName = "rolesToNames"),
        @Mapping(target = "statusUser", expression = "java(user.getStatusUser() != null ? user.getStatusUser().name() : null)")
    })
    UserDTO toUserDTO(User user);

    @Mappings({
        @Mapping(target = "roles", ignore = true),
        @Mapping(target = "password", ignore = true),
        @Mapping(target = "statusUser", expression = "java(dto.getStatusUser() != null ? br.com.uvmarker.makerhub_web.user.entity.StatusUser.valueOf(dto.getStatusUser()) : null)")
    })
    User toUser(UserDTO dto);

    List<UserDTO> toUserDTOList(List<User> users);
    List<User> toUserList(List<UserDTO> dtos);

    UserSummaryDTO toUserSummaryDTO(User user);
    @Mappings({
        @Mapping(target = "password", ignore = true),
        @Mapping(target = "isActive", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "statusUser", ignore = true),
        @Mapping(target = "roles", ignore = true)
    })
    User toUserSummary(UserSummaryDTO dto);

    List<UserSummaryDTO> toUserSummaryDTOList(List<User> users);
    List<User> toUserSummaryList(List<UserSummaryDTO> dtos);

    // DTO de entrada → ignora campos gerados automaticamente
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "roles", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true),
        @Mapping(target = "statusUser", ignore = true),
        @Mapping(target = "profileImageUrl", ignore = true)
    })
    User toUser(UserRegisterDTO dto);

    List<User> toUserRegisterList(List<UserRegisterDTO> dtos);

    // ===================== ROLE =====================
    RoleDTO toRoleDTO(Role role);
    @Mappings({
        @Mapping(target = "isActive", ignore = true),
        @Mapping(target = "createdAt", ignore = true)
    })
    Role toRole(RoleDTO dto);

    List<RoleDTO> toRoleDTOList(List<Role> roles);
    List<Role> toRoleList(List<RoleDTO> dtos);

    // ===================== LOGIN =====================
    @Mappings({
        @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToNames"),
        @Mapping(source = "statusUser", target = "statusUser"),
        @Mapping(target = "accessToken", ignore = true)
    })
    LoginResponseDTO toLoginResponseDTO(User user);

    // DTO de entrada → ignora campos gerados automaticamente
    @Mappings({
        @Mapping(target = "roles", ignore = true),
        @Mapping(target = "statusUser", ignore = true),
        @Mapping(target = "password", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    User toUser(LoginResponseDTO dto);

    List<LoginResponseDTO> toLoginResponseDTOList(List<User> users);
    List<User> toUserLoginResponseList(List<LoginResponseDTO> dtos);

    // ===================== TOKEN =====================
    @Mappings({
        @Mapping(source = "user.id", target = "userId")
    })
    UserTokenDTO toUserTokenDTO(UserToken token);

    @Mappings({
        @Mapping(source = "userId", target = "user.id")
    })
    UserToken toUserToken(UserTokenDTO dto);

    List<UserTokenDTO> toUserTokenDTOList(List<UserToken> tokens);
    List<UserToken> toUserTokenList(List<UserTokenDTO> dtos);

    // ===================== PAGE SUPPORT =====================
    default Page<UserDTO> toUserDTOPage(Page<User> users) {
        return new PageImpl<>(toUserDTOList(users.getContent()), users.getPageable(), users.getTotalElements());
    }

    default Page<User> toUserPage(Page<UserDTO> dtos) {
        return new PageImpl<>(toUserList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<UserSummaryDTO> toUserSummaryDTOPage(Page<User> users) {
        return new PageImpl<>(toUserSummaryDTOList(users.getContent()), users.getPageable(), users.getTotalElements());
    }

    default Page<User> toUserSummaryPage(Page<UserSummaryDTO> dtos) {
        return new PageImpl<>(toUserSummaryList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<RoleDTO> toRoleDTOPage(Page<Role> roles) {
        return new PageImpl<>(toRoleDTOList(roles.getContent()), roles.getPageable(), roles.getTotalElements());
    }

    default Page<Role> toRolePage(Page<RoleDTO> dtos) {
        return new PageImpl<>(toRoleList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<UserTokenDTO> toUserTokenDTOPage(Page<UserToken> tokens) {
        return new PageImpl<>(toUserTokenDTOList(tokens.getContent()), tokens.getPageable(), tokens.getTotalElements());
    }

    default Page<UserToken> toUserTokenPage(Page<UserTokenDTO> dtos) {
        return new PageImpl<>(toUserTokenList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<LoginResponseDTO> toLoginResponseDTOPage(Page<User> users) {
        return new PageImpl<>(toLoginResponseDTOList(users.getContent()), users.getPageable(), users.getTotalElements());
    }

    default Page<User> toUserLoginResponsePage(Page<LoginResponseDTO> dtos) {
        return new PageImpl<>(toUserLoginResponseList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== HELPERS =====================    
    @Named("rolesToNames")
    default List<String> rolesToNames(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
    }
}