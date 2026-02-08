package br.com.uvmarker.makerhub_web.person.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.uvmarker.makerhub_web.person.domain.entity.*;
import br.com.uvmarker.makerhub_web.person.dto.*;

@Mapper(componentModel = "spring")
public interface CentralMapperPerson {

    CentralMapperPerson INSTANCE = Mappers.getMapper(CentralMapperPerson.class);

    // ===================== PERSON =====================
    @Mappings({
        @Mapping(source = "type.id", target = "typeId")
    })
    PersonDTO toPersonDTO(Person person);

    @Mappings({
        @Mapping(source = "typeId", target = "type.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    Person toPerson(PersonDTO dto);

    List<PersonDTO> toPersonDTOList(List<Person> persons);
    List<Person> toPersonList(List<PersonDTO> dtos);

    PersonSummaryDTO toPersonSummaryDTO(Person person);

    @Mappings({
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    Person toPersonSummary(PersonSummaryDTO dto);

    List<PersonSummaryDTO> toPersonSummaryDTOList(List<Person> persons);
    List<Person> toPersonSummaryList(List<PersonSummaryDTO> dtos);

    // ===================== PERSON CONTACT =====================
    @Mappings({
        @Mapping(source = "person.id", target = "personId")
    })
    PersonContactDTO toPersonContactDTO(PersonContact contact);

    @Mappings({
        @Mapping(source = "personId", target = "person.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    PersonContact toPersonContact(PersonContactDTO dto);

    List<PersonContactDTO> toPersonContactDTOList(List<PersonContact> contacts);
    List<PersonContact> toPersonContactList(List<PersonContactDTO> dtos);

    // ===================== GUARDIAN-STUDENT =====================
    @Mappings({
        @Mapping(source = "guardian.id", target = "guardianId"),
        @Mapping(source = "student.id", target = "studentId"),
        @Mapping(source = "relationship.id", target = "relationshipId")
    })
    GuardianStudentDTO toGuardianStudentDTO(GuardianStudent gs);

    @Mappings({
        @Mapping(source = "guardianId", target = "guardian.id"),
        @Mapping(source = "studentId", target = "student.id"),
        @Mapping(source = "relationshipId", target = "relationship.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    GuardianStudent toGuardianStudent(GuardianStudentDTO dto);

    List<GuardianStudentDTO> toGuardianStudentDTOList(List<GuardianStudent> gsList);
    List<GuardianStudent> toGuardianStudentList(List<GuardianStudentDTO> dtos);

    // ===================== STUDENT FILE =====================
    @Mappings({
        @Mapping(source = "student.id", target = "studentId")
    })
    StudentFileDTO toStudentFileDTO(StudentFile file);

    @Mappings({
        @Mapping(source = "studentId", target = "student.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    StudentFile toStudentFile(StudentFileDTO dto);

    List<StudentFileDTO> toStudentFileDTOList(List<StudentFile> files);
    List<StudentFile> toStudentFileList(List<StudentFileDTO> dtos);

    // ===================== PAGE SUPPORT =====================
    default Page<PersonDTO> toPersonDTOPage(Page<Person> persons) {
        return new PageImpl<>(toPersonDTOList(persons.getContent()), persons.getPageable(), persons.getTotalElements());
    }

    default Page<Person> toPersonPage(Page<PersonDTO> dtos) {
        return new PageImpl<>(toPersonList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<PersonSummaryDTO> toPersonSummaryDTOPage(Page<Person> persons) {
        return new PageImpl<>(toPersonSummaryDTOList(persons.getContent()), persons.getPageable(), persons.getTotalElements());
    }

    default Page<Person> toPersonSummaryPage(Page<PersonSummaryDTO> dtos) {
        return new PageImpl<>(toPersonSummaryList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<PersonContactDTO> toPersonContactDTOPage(Page<PersonContact> contacts) {
        return new PageImpl<>(toPersonContactDTOList(contacts.getContent()), contacts.getPageable(), contacts.getTotalElements());
    }

    default Page<PersonContact> toPersonContactPage(Page<PersonContactDTO> dtos) {
        return new PageImpl<>(toPersonContactList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<GuardianStudentDTO> toGuardianStudentDTOPage(Page<GuardianStudent> gsList) {
        return new PageImpl<>(toGuardianStudentDTOList(gsList.getContent()), gsList.getPageable(), gsList.getTotalElements());
    }

    default Page<GuardianStudent> toGuardianStudentPage(Page<GuardianStudentDTO> dtos) {
        return new PageImpl<>(toGuardianStudentList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    default Page<StudentFileDTO> toStudentFileDTOPage(Page<StudentFile> files) {
        return new PageImpl<>(toStudentFileDTOList(files.getContent()), files.getPageable(), files.getTotalElements());
    }

    default Page<StudentFile> toStudentFilePage(Page<StudentFileDTO> dtos) {
        return new PageImpl<>(toStudentFileList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }
}