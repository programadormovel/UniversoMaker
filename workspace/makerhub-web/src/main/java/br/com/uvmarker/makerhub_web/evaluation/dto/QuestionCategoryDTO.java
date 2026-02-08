package br.com.uvmarker.makerhub_web.evaluation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCategoryDTO {

    private Long id;

    private Long typeId;
    
    @NotBlank
    private String categoryName;

    private String description;

}
