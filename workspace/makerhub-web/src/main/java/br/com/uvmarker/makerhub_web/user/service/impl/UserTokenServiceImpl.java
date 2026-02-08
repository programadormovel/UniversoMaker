package br.com.uvmarker.makerhub_web.user.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.common.enums.TokenType;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.entity.UserToken;
import br.com.uvmarker.makerhub_web.user.domain.repository.UserTokenRepository;
import br.com.uvmarker.makerhub_web.user.service.UserTokenService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

    private final UserTokenRepository usertokenRepository;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    @SuppressWarnings("null")
    @Override
    public void saveRefreshToken(User user, String token, LocalDateTime expiresAt) {
        UserToken userToken = UserToken.builder()
            .user(user)
            .token(token)
            .tokenType(TokenType.REFRESH)
            .expiresAt(expiresAt.plus(refreshExpirationMs, ChronoUnit.MILLIS))
            .build();

        usertokenRepository.save(userToken);
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        return usertokenRepository.findByToken(token)
            .filter(t -> !t.getIsRevoked() && t.getExpiresAt().isAfter(LocalDateTime.now()))
            .isPresent();
    }

    @Override
    public void revokeToken(String token) {
        usertokenRepository.findByToken(token).ifPresent(t -> {
            t.setIsRevoked(true);
            usertokenRepository.save(t);
        });
    }

    @Override
    public void revokeAllUserTokens(User user) {
        List<UserToken> tokens = usertokenRepository.findAllByUserAndIsRevokedFalse(user);
        tokens.forEach(t -> t.setIsRevoked(true));
        usertokenRepository.saveAll(tokens);
    }

    @Override
    public Optional<UserToken> getByToken(String token) {
        return usertokenRepository.findByToken(token);
    }
}