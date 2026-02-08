package br.com.uvmarker.makerhub_web.evaluation.dto;

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
public class DiagnosticResultDTO {

    private Long id;

    @NotNull
    private Long evaluationId;

    @NotNull
    private Long professionalId;

    @NotNull
    private Long classificationId;

    @NotBlank
    private String recommendations;

    private Boolean isActive;

}
