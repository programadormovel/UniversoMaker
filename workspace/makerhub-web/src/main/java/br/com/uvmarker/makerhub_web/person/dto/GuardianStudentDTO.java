package br.com.uvmarker.makerhub_web.person.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardianStudentDTO {

    @NotNull
    private Long id;
    private Long guardianId;
    private Long studentId;
    private Long relationshipId;
    private String notes;
    
}
