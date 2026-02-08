package br.com.uvmarker.makerhub_web.pei.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeiFileDTO {

    private Long id;

    @NotNull
    private Long evaluationId;

    @NotBlank
    @Size(max = 255)
    private String fileUrl;

    private String description;

    private Boolean isActive;

}
