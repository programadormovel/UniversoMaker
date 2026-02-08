package br.com.uvmarker.makerhub_web.pei.dto;

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
public class PeiMonitoringDTO {

    private Long id;

    @NotNull
    private Long peiId;

    @NotNull
    private Long professionalId;

    @NotBlank
    private String notes;


}
