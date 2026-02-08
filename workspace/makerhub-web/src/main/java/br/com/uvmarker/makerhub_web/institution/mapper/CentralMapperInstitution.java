package br.com.uvmarker.makerhub_web.institution.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.uvmarker.makerhub_web.institution.domain.entity.*;
import br.com.uvmarker.makerhub_web.institution.dto.*;

@Mapper(componentModel = "spring")
public interface CentralMapperInstitution {

    CentralMapperInstitution INSTANCE = Mappers.getMapper(CentralMapperInstitution.class);

    // ===================== INSTITUTION =====================
    @Mappings({
        @Mapping(source = "type.id", target = "typeId")
    })
    InstitutionDTO toInstitutionDTO(Institution entity);

    @Mappings({
        @Mapping(source = "typeId", target = "type.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    Institution toInstitution(InstitutionDTO dto);

    List<InstitutionDTO> toInstitutionDTOList(List<Institution> entities);
    List<Institution> toInstitutionList(List<InstitutionDTO> dtos);

    default Page<InstitutionDTO> toInstitutionDTOPage(Page<Institution> entities) {
        return new PageImpl<>(toInstitutionDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<Institution> toInstitutionPage(Page<InstitutionDTO> dtos) {
        return new PageImpl<>(toInstitutionList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== INSTITUTION CONTACT =====================
    @Mappings({
        @Mapping(source = "institution.id", target = "institutionId")
    })
    InstitutionContactDTO toInstitutionContactDTO(InstitutionContact entity);

    @Mappings({
        @Mapping(source = "institutionId", target = "institution.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    InstitutionContact toInstitutionContact(InstitutionContactDTO dto);

    List<InstitutionContactDTO> toInstitutionContactDTOList(List<InstitutionContact> entities);
    List<InstitutionContact> toInstitutionContactList(List<InstitutionContactDTO> dtos);

    default Page<InstitutionContactDTO> toInstitutionContactDTOPage(Page<InstitutionContact> entities) {
        return new PageImpl<>(toInstitutionContactDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<InstitutionContact> toInstitutionContactPage(Page<InstitutionContactDTO> dtos) {
        return new PageImpl<>(toInstitutionContactList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== INSTITUTION CLASS =====================
    @Mappings({
        @Mapping(source = "institution.id", target = "institutionId")
    })
    InstitutionClassDTO toInstitutionClassDTO(InstitutionClass entity);

    @Mappings({
        @Mapping(source = "institutionId", target = "institution.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    InstitutionClass toInstitutionClass(InstitutionClassDTO dto);

    List<InstitutionClassDTO> toInstitutionClassDTOList(List<InstitutionClass> entities);
    List<InstitutionClass> toInstitutionClassList(List<InstitutionClassDTO> dtos);

    default Page<InstitutionClassDTO> toInstitutionClassDTOPage(Page<InstitutionClass> entities) {
        return new PageImpl<>(toInstitutionClassDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<InstitutionClass> toInstitutionClassPage(Page<InstitutionClassDTO> dtos) {
        return new PageImpl<>(toInstitutionClassList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== PROFESSIONAL CLASS =====================
    @Mappings({
        @Mapping(source = "professionalRole.id", target = "professionalRoleId"),
        @Mapping(source = "institutionClass.id", target = "classId")
    })
    ProfessionalClassDTO toProfessionalClassDTO(ProfessionalClass entity);

    @Mappings({
        @Mapping(source = "professionalRoleId", target = "professionalRole.id"),
        @Mapping(source = "classId", target = "institutionClass.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    ProfessionalClass toProfessionalClass(ProfessionalClassDTO dto);

    List<ProfessionalClassDTO> toProfessionalClassDTOList(List<ProfessionalClass> entities);
    List<ProfessionalClass> toProfessionalClassList(List<ProfessionalClassDTO> dtos);

    default Page<ProfessionalClassDTO> toProfessionalClassDTOPage(Page<ProfessionalClass> entities) {
        return new PageImpl<>(toProfessionalClassDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<ProfessionalClass> toProfessionalClassPage(Page<ProfessionalClassDTO> dtos) {
        return new PageImpl<>(toProfessionalClassList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== PROFESSIONAL INSTITUTION =====================
    @Mappings({
        @Mapping(source = "professionalRole.id", target = "professionalRoleId"),
        @Mapping(source = "institution.id", target = "institutionId")
    })
    ProfessionalInstitutionDTO toProfessionalInstitutionDTO(ProfessionalInstitution entity);

    @Mappings({
        @Mapping(source = "professionalRoleId", target = "professionalRole.id"),
        @Mapping(source = "institutionId", target = "institution.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    ProfessionalInstitution toProfessionalInstitution(ProfessionalInstitutionDTO dto);

    List<ProfessionalInstitutionDTO> toProfessionalInstitutionDTOList(List<ProfessionalInstitution> entities);
    List<ProfessionalInstitution> toProfessionalInstitutionList(List<ProfessionalInstitutionDTO> dtos);

    default Page<ProfessionalInstitutionDTO> toProfessionalInstitutionDTOPage(Page<ProfessionalInstitution> entities) {
        return new PageImpl<>(toProfessionalInstitutionDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<ProfessionalInstitution> toProfessionalInstitutionPage(Page<ProfessionalInstitutionDTO> dtos) {
        return new PageImpl<>(toProfessionalInstitutionList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== STUDENT CLASS =====================
    @Mappings({
        @Mapping(source = "institutionClass.id", target = "classId")
    })
    StudentClassDTO toStudentClassDTO(StudentClass entity);

    @Mappings({
        @Mapping(source = "classId", target = "institutionClass.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    StudentClass toStudentClass(StudentClassDTO dto);

    List<StudentClassDTO> toStudentClassDTOList(List<StudentClass> entities);
    List<StudentClass> toStudentClassList(List<StudentClassDTO> dtos);

    default Page<StudentClassDTO> toStudentClassDTOPage(Page<StudentClass> entities) {
        return new PageImpl<>(toStudentClassDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<StudentClass> toStudentClassPage(Page<StudentClassDTO> dtos) {
        return new PageImpl<>(toStudentClassList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }
}