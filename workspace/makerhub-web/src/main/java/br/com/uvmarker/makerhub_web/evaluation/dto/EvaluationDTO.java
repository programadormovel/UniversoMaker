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
public class EvaluationDTO {

    private Long id;

    @NotNull
    private Long typeId;

    @NotNull
    private Long studentId;

    @NotNull
    private Long professionalId;

    @NotBlank
    private String notes;

    private Integer rate;

    private Boolean isActive;

}
