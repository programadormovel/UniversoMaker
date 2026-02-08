package br.com.uvmarker.makerhub_web.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String fullname;
    private String username;

    @Nullable
    private String profileImageUrl;
    private Boolean isActive;

    @Nullable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String statusUser;	
    private List<String> roleNames;
    
}
