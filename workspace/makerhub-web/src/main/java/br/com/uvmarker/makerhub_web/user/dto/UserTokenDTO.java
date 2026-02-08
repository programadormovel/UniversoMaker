package br.com.uvmarker.makerhub_web.user.dto;

import java.time.LocalDateTime;

import br.com.uvmarker.makerhub_web.common.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDTO {

    private Long id;
    private Long userId; 
    private String token;
    private TokenType tokenType;
    private Boolean isRevoked;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
