package br.com.uvmarker.makerhub_web.user.dto;

import java.util.List;

import br.com.uvmarker.makerhub_web.common.enums.StatusUser;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private Long id;

    @Nullable
	private String accessToken;
	private String fullname;
	private String username;
    private String profileImageUrl;
    private List<String> roles;
    private StatusUser statusUser;

}
