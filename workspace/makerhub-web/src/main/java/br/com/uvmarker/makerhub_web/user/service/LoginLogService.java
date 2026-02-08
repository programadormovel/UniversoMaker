
package br.com.uvmarker.makerhub_web.user.service;

import java.util.List;
import java.util.Optional;

import br.com.uvmarker.makerhub_web.user.domain.entity.LoginLog;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;

public interface LoginLogService {

    List<LoginLog> findAll();

    Optional<LoginLog> findById(Long id);

    void saveLoginLog(User user, String ipAddress, String userAgent, Boolean success);

    void deleteById(Long id);
}
