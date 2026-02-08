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
public class InstitutionDTO {

    @NotNull
    private Long id;
    private Long userId;
    private Long typeId;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String description;
    private String zipCode;
    private String addressNr;
	private String addressInfo;

}
