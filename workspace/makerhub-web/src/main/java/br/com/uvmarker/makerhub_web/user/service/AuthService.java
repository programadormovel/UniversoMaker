package br.com.uvmarker.makerhub_web.user.service;

import br.com.uvmarker.makerhub_web.user.dto.LoginRequestDTO;
import br.com.uvmarker.makerhub_web.user.dto.LoginResponseDTO;
import br.com.uvmarker.makerhub_web.user.dto.TokenRefreshRequest;
import br.com.uvmarker.makerhub_web.user.dto.TokenRefreshResponse;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request, String ipAddress, String userAgent);

    TokenRefreshResponse refreshToken(TokenRefreshRequest request);
    
}
