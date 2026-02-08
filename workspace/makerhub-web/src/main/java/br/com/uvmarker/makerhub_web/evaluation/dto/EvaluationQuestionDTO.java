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
public class EvaluationQuestionDTO {

    private Long id;

    @NotBlank
    private String question;

    @NotNull
    private Long categoryId;

    private Boolean isActive;

}
