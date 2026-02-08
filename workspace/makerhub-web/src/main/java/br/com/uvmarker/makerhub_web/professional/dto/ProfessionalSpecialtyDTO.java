package br.com.uvmarker.makerhub_web.professional.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalSpecialtyDTO {

    private Long id;

    private Long personId;

    private Long specialtyId;

    private String documentNr;
    
}
