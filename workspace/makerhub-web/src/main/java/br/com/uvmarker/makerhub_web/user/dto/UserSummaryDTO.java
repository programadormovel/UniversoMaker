package br.com.uvmarker.makerhub_web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSummaryDTO {
    
    private Long id;
    private String fullname;
    private String username;
    private String profileImageUrl;

}
