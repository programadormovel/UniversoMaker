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
public class EvaluationAnswerDTO {

    private Long id;

    @NotNull
    private Long evaluationId;

    @NotNull
    private Long questionId;

    @NotBlank
    private String answerText;

    private Integer score;

    private Boolean isActive;

}
