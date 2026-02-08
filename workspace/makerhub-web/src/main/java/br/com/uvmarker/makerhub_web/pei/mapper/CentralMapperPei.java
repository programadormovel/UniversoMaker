package br.com.uvmarker.makerhub_web.pei.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.uvmarker.makerhub_web.pei.domain.entity.*;
import br.com.uvmarker.makerhub_web.pei.dto.*;

@Mapper(componentModel = "spring")
public interface CentralMapperPei {

    CentralMapperPei INSTANCE = Mappers.getMapper(CentralMapperPei.class);

    // ===================== EDUCATIONAL NEED PEI =====================
    @Mappings({
        @Mapping(source = "pei.id", target = "peiId"),
        @Mapping(source = "educationalNeed.id", target = "educationalNeedId")
    })
    EducationalNeedPeiDTO toEducationalNeedPeiDTO(EducationalNeedPei entity);

    @Mappings({
        @Mapping(source = "peiId", target = "pei.id"),
        @Mapping(source = "educationalNeedId", target = "educationalNeed.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    EducationalNeedPei toEducationalNeedPei(EducationalNeedPeiDTO dto);

    List<EducationalNeedPeiDTO> toEducationalNeedPeiDTOList(List<EducationalNeedPei> entities);
    List<EducationalNeedPei> toEducationalNeedPeiList(List<EducationalNeedPeiDTO> dtos);

    default Page<EducationalNeedPeiDTO> toEducationalNeedPeiDTOPage(Page<EducationalNeedPei> entities) {
        return new PageImpl<>(toEducationalNeedPeiDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<EducationalNeedPei> toEducationalNeedPeiPage(Page<EducationalNeedPeiDTO> dtos) {
        return new PageImpl<>(toEducationalNeedPeiList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== EVALUATION FUNCTION PEI =====================
    @Mappings({
        @Mapping(source = "pei.id", target = "peiId"),
        @Mapping(source = "function.id", target = "functionId")
    })
    EvaluationFunctionPeiDTO toEvaluationFunctionPeiDTO(EvaluationFunctionPei entity);

    @Mappings({
        @Mapping(source = "peiId", target = "pei.id"),
        @Mapping(source = "functionId", target = "function.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    EvaluationFunctionPei toEvaluationFunctionPei(EvaluationFunctionPeiDTO dto);

    List<EvaluationFunctionPeiDTO> toEvaluationFunctionPeiDTOList(List<EvaluationFunctionPei> entities);
    List<EvaluationFunctionPei> toEvaluationFunctionPeiList(List<EvaluationFunctionPeiDTO> dtos);

    default Page<EvaluationFunctionPeiDTO> toEvaluationFunctionPeiDTOPage(Page<EvaluationFunctionPei> entities) {
        return new PageImpl<>(toEvaluationFunctionPeiDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<EvaluationFunctionPei> toEvaluationFunctionPeiPage(Page<EvaluationFunctionPeiDTO> dtos) {
        return new PageImpl<>(toEvaluationFunctionPeiList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== PEI =====================
    PeiDTO toPeiDTO(Pei entity);

    @Mappings({
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    Pei toPei(PeiDTO dto);

    List<PeiDTO> toPeiDTOList(List<Pei> entities);
    List<Pei> toPeiList(List<PeiDTO> dtos);

    default Page<PeiDTO> toPeiDTOPage(Page<Pei> entities) {
        return new PageImpl<>(toPeiDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<Pei> toPeiPage(Page<PeiDTO> dtos) {
        return new PageImpl<>(toPeiList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== PEI FILE =====================
    @Mappings({
        @Mapping(source = "pei.id", target = "evaluationId")
    })
    PeiFileDTO toPeiFileDTO(PeiFile entity);

    @Mappings({
        @Mapping(source = "evaluationId", target = "pei.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    PeiFile toPeiFile(PeiFileDTO dto);

    List<PeiFileDTO> toPeiFileDTOList(List<PeiFile> entities);
    List<PeiFile> toPeiFileList(List<PeiFileDTO> dtos);

    default Page<PeiFileDTO> toPeiFileDTOPage(Page<PeiFile> entities) {
        return new PageImpl<>(toPeiFileDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<PeiFile> toPeiFilePage(Page<PeiFileDTO> dtos) {
        return new PageImpl<>(toPeiFileList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== PEI MONITORING =====================
    @Mappings({
        @Mapping(source = "pei.id", target = "peiId")
    })
    PeiMonitoringDTO toPeiMonitoringDTO(PeiMonitoring entity);

    @Mappings({
        @Mapping(source = "peiId", target = "pei.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    PeiMonitoring toPeiMonitoring(PeiMonitoringDTO dto);

    List<PeiMonitoringDTO> toPeiMonitoringDTOList(List<PeiMonitoring> entities);
    List<PeiMonitoring> toPeiMonitoringList(List<PeiMonitoringDTO> dtos);

    default Page<PeiMonitoringDTO> toPeiMonitoringDTOPage(Page<PeiMonitoring> entities) {
        return new PageImpl<>(toPeiMonitoringDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<PeiMonitoring> toPeiMonitoringPage(Page<PeiMonitoringDTO> dtos) {
        return new PageImpl<>(toPeiMonitoringList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }
}
