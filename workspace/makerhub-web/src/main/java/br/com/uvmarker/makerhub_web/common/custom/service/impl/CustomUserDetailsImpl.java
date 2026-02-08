package br.com.uvmarker.makerhub_web.common.custom.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.uvmarker.makerhub_web.common.enums.StatusUser;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import lombok.Getter;

@Getter
public class CustomUserDetailsImpl implements UserDetails {

    private final Long id;
    private final String fullname;
    private final String username;
    private final String password;
    private final String profileImageUrl;
    private final boolean isActive;
    private final StatusUser statusUser;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetailsImpl(User user) {
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.profileImageUrl = user.getProfileImageUrl();
        this.isActive = user.getIsActive();
        this.statusUser = user.getStatusUser();
        this.authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
            .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}