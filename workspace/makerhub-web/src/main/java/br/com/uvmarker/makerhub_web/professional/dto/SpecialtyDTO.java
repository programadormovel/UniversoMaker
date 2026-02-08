package br.com.uvmarker.makerhub_web.professional.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialtyDTO {

    private Long id;

    @NotBlank
    private String specialtyName;

    private String description;

    private Long councilId;

}
