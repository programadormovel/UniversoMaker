package br.com.uvmarker.makerhub_web.user.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.entity.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    
    Optional<UserToken> findByToken(String token);
    List<UserToken> findAllByUserAndIsRevokedFalse(User user);

}