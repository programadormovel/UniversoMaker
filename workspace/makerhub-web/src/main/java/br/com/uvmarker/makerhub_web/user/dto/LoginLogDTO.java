package br.com.uvmarker.makerhub_web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginLogDTO {
    private Long id;
    private Long userId;
    private String ipAddress;
    private String userAgent;
    private Boolean success;
    private LocalDateTime createdAt;
}