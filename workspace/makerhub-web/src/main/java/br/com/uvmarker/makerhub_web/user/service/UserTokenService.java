package br.com.uvmarker.makerhub_web.user.service;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.entity.UserToken;

public interface UserTokenService {

    void saveRefreshToken(User user, String token, LocalDateTime expiresAt);
    boolean isRefreshTokenValid(String token);
    void revokeToken(String token);
    void revokeAllUserTokens(User user);
    Optional<UserToken> getByToken(String token);
}
