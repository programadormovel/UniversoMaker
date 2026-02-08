package br.com.uvmarker.makerhub_web.person.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonSummaryDTO {

    @NotNull
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String document;
    private String personImageUrl;
    private Long userId;

}
