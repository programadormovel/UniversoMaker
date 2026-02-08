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
public class InstitutionContactDTO {

    @NotNull
    private Long id;
    private Long institutionId;
    private Long typeId;

    @NotBlank(message = "O contato é obrigatório")
    private String contact;
    private String notes;

}
