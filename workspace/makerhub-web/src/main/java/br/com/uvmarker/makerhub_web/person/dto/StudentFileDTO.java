package br.com.uvmarker.makerhub_web.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentFileDTO {

    @NotNull
    private Long id;
    private Long studentId;

    @NotBlank
    private String fileUrl;
    private String description;

}
