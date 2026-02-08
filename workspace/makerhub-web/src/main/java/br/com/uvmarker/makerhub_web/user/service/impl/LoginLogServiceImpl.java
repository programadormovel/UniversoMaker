package br.com.uvmarker.makerhub_web.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.user.domain.entity.LoginLog;
import br.com.uvmarker.makerhub_web.user.domain.entity.User;
import br.com.uvmarker.makerhub_web.user.domain.repository.LoginLogRepository;
import br.com.uvmarker.makerhub_web.user.service.LoginLogService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogRepository loginlogRepository;

    @Override
    public List<LoginLog> findAll() {
        return loginlogRepository.findAll();
    }

    @Override
    public Optional<LoginLog> findById(Long id) {
        return loginlogRepository.findById(id);
    }

    @Override
    public void saveLoginLog(User user, String ipAddress, String userAgent, Boolean success) {
        LoginLog loginLog = LoginLog.builder()
            .user(user)
            .ipAddress(ipAddress)
            .userAgent(userAgent)
            .success(success)
            .build();

        loginlogRepository.save(loginLog);
    }

    @Override
    public void deleteById(Long id) {
        loginlogRepository.deleteById(id);
    }
}
