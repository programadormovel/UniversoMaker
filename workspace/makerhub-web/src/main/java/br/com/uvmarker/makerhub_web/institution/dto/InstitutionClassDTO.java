package br.com.uvmarker.makerhub_web.institution.dto;

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
public class InstitutionClassDTO {

    @NotNull
    private Long id;

    @NotBlank(message = "O contato é obrigatório")
    private String name;
    private String notes;
    private String academicYear;
    private Long institutionId;

}
