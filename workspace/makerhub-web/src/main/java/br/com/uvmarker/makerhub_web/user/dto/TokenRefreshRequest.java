package br.com.uvmarker.makerhub_web.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;

}
