package br.com.uvmarker.makerhub_web.institution.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentClassDTO {

    @NotNull
    private Long id;
    private Long studentId;
    private Long classId;

    private String notes;
    private Boolean isActive;
    
}
