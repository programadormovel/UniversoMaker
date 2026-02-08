package br.com.uvmarker.makerhub_web.professional.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.uvmarker.makerhub_web.professional.domain.entity.ProfessionalSpecialty;
import br.com.uvmarker.makerhub_web.professional.domain.entity.Specialty;
import br.com.uvmarker.makerhub_web.professional.dto.ProfessionalSpecialtyDTO;
import br.com.uvmarker.makerhub_web.professional.dto.SpecialtyDTO;

@Mapper(componentModel = "spring")
public interface CentralMapperProfessional {

    CentralMapperProfessional INSTANCE = Mappers.getMapper(CentralMapperProfessional.class);

    // ===================== PROFESSIONAL SPECIALTY =====================
    @Mappings({
        @Mapping(source = "specialty.id", target = "specialtyId")
    })
    ProfessionalSpecialtyDTO toProfessionalSpecialtyDTO(ProfessionalSpecialty entity);

    @Mappings({
        @Mapping(source = "specialtyId", target = "specialty.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    ProfessionalSpecialty toProfessionalSpecialty(ProfessionalSpecialtyDTO dto);

    List<ProfessionalSpecialtyDTO> toProfessionalSpecialtyDTOList(List<ProfessionalSpecialty> entities);
    List<ProfessionalSpecialty> toProfessionalSpecialtyList(List<ProfessionalSpecialtyDTO> dtos);

    default Page<ProfessionalSpecialtyDTO> toProfessionalSpecialtyDTOPage(Page<ProfessionalSpecialty> entities) {
        return new PageImpl<>(toProfessionalSpecialtyDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<ProfessionalSpecialty> toProfessionalSpecialtyPage(Page<ProfessionalSpecialtyDTO> dtos) {
        return new PageImpl<>(toProfessionalSpecialtyList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== SPECIALTY =====================
    @Mappings({
        @Mapping(source = "council.id", target = "councilId")
    })
    SpecialtyDTO toSpecialtyDTO(Specialty entity);

    @Mappings({
        @Mapping(source = "councilId", target = "council.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    Specialty toSpecialty(SpecialtyDTO dto);

    List<SpecialtyDTO> toSpecialtyDTOList(List<Specialty> entities);
    List<Specialty> toSpecialtyList(List<SpecialtyDTO> dtos);

    default Page<SpecialtyDTO> toSpecialtyDTOPage(Page<Specialty> entities) {
        return new PageImpl<>(toSpecialtyDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<Specialty> toSpecialtyPage(Page<SpecialtyDTO> dtos) {
        return new PageImpl<>(toSpecialtyList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

}