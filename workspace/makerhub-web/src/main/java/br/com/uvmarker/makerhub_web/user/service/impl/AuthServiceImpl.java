package br.com.uvmarker.makerhub_web.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.common.custom.service.impl.CustomUserDetailsImpl;
import br.com.uvmarker.makerhub_web.common.jwt.JwtService;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.entity.UserToken;
import br.com.uvmarker.makerhub_web.user.domain.repository.UserRepository;
import br.com.uvmarker.makerhub_web.user.dto.LoginRequestDTO;
import br.com.uvmarker.makerhub_web.user.dto.LoginResponseDTO;
import br.com.uvmarker.makerhub_web.user.dto.TokenRefreshRequest;
import br.com.uvmarker.makerhub_web.user.dto.TokenRefreshResponse;
import br.com.uvmarker.makerhub_web.user.mapper.CentralMapperUser;
import br.com.uvmarker.makerhub_web.user.service.AuthService;
import br.com.uvmarker.makerhub_web.user.service.LoginLogService;
import br.com.uvmarker.makerhub_web.user.service.UserTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserTokenService tokenService;
    private final LoginLogService loginLogService;
    private final CentralMapperUser mapper;

    @SuppressWarnings("null")
    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO request, String ipAddress, String userAgent) {
        // log.info("Tentativa de login para usuário: {}", request.getUsername());
        // Autentica o usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();

        // Gera tokens
        String accessToken = jwtService.generateAccessToken((UserDetails) authentication.getPrincipal());
        String refreshToken = jwtService.generateRefreshToken((UserDetails) authentication.getPrincipal());

        User user = userRepository.getReferenceById(userDetails.getId());

        // Salva o refresh token no banco
        tokenService.saveRefreshToken(user, refreshToken, LocalDateTime.now());

        loginLogService.saveLoginLog(user, ipAddress, userAgent, true);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        LoginResponseDTO loginResponseDTO = mapper.toLoginResponseDTO(user);
        loginResponseDTO.setAccessToken(accessToken);
        loginResponseDTO.setRoles(roles);

        return loginResponseDTO;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!tokenService.isRefreshTokenValid(refreshToken)) {
            throw new RuntimeException("Refresh token inválido ou expirado.");
        }

        UserToken userToken = tokenService.getByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Token não encontrado"));

        User user = userToken.getUser();

        String newAccessToken = jwtService.generateAccessToken((UserDetails) user);
        String newRefreshToken = jwtService.generateRefreshToken((UserDetails) user);

        // Salva o novo refresh token no banco
        tokenService.saveRefreshToken(user, newRefreshToken, LocalDateTime.now());

        // Retorna os novos tokens
        return TokenRefreshResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

    }


    /* 
    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtService.generateToken(authentication);

        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();  

		List<String> roles = userDetails.getAuthorities()
										.stream()
										.map(item -> item.getAuthority())
										.collect(Collectors.toList());
		Hibernate.initialize(roles);
		
		return new LoginResponseDTO(
					userDetails.getId(), 
					accessToken, 
	                userDetails.getFullname(), 
	                userDetails.getUsername(), 
	                roles,
	                userDetails.getUserStatus());
    }
     */
}
