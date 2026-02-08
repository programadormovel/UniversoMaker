package br.com.uvmarker.makerhub_web.person.dto;

import java.time.LocalDate;

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
public class PersonDTO {

    @NotNull
    private Long id;
    private Long userId;
    private Long typeId;

    @NotBlank(message = "O nome completo é obrigatório")
    private String name;

    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate birthDate;

    @NotBlank(message = "O gênero é obrigatório")
    private String gender;

    @NotBlank(message = "O documento é obrigatório")
    private String document;
	private String personImageUrl;

    
}
