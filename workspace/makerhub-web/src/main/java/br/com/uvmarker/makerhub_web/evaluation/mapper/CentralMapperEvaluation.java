package br.com.uvmarker.makerhub_web.evaluation.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.uvmarker.makerhub_web.evaluation.domain.entity.*;
import br.com.uvmarker.makerhub_web.evaluation.dto.*;

@Mapper(componentModel = "spring")
public interface CentralMapperEvaluation {

    CentralMapperEvaluation INSTANCE = Mappers.getMapper(CentralMapperEvaluation.class);

    // ===================== CLINICAL HISTORY =====================
    @Mappings({
        @Mapping(source = "item.id", target = "itemId")
    })
    ClinicalHistoryDTO toClinicalHistoryDTO(ClinicalHistory entity);

    @Mappings({
        @Mapping(source = "itemId", target = "item.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    ClinicalHistory toClinicalHistory(ClinicalHistoryDTO dto);

    List<ClinicalHistoryDTO> toClinicalHistoryDTOList(List<ClinicalHistory> entities);
    List<ClinicalHistory> toClinicalHistoryList(List<ClinicalHistoryDTO> dtos);

    default Page<ClinicalHistoryDTO> toClinicalHistoryDTOPage(Page<ClinicalHistory> entities) {
        return new PageImpl<>(toClinicalHistoryDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<ClinicalHistory> toClinicalHistoryPage(Page<ClinicalHistoryDTO> dtos) {
        return new PageImpl<>(toClinicalHistoryList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== DIAGNOSTIC RESULT =====================
    @Mappings({
        @Mapping(source = "evaluation.id", target = "evaluationId"),
        @Mapping(source = "classification.id", target = "classificationId")
    })
    DiagnosticResultDTO toDiagnosticResultDTO(DiagnosticResult entity);

    @Mappings({
        @Mapping(source = "evaluationId", target = "evaluation.id"),
        @Mapping(source = "classificationId", target = "classification.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    DiagnosticResult toDiagnosticResult(DiagnosticResultDTO dto);

    List<DiagnosticResultDTO> toDiagnosticResultDTOList(List<DiagnosticResult> entities);
    List<DiagnosticResult> toDiagnosticResultList(List<DiagnosticResultDTO> dtos);

    default Page<DiagnosticResultDTO> toDiagnosticResultDTOPage(Page<DiagnosticResult> entities) {
        return new PageImpl<>(toDiagnosticResultDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<DiagnosticResult> toDiagnosticResultPage(Page<DiagnosticResultDTO> dtos) {
        return new PageImpl<>(toDiagnosticResultList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== EVALUATION =====================
    @Mappings({
        @Mapping(source = "type.id", target = "typeId")
    })
    EvaluationDTO toEvaluationDTO(Evaluation entity);

    @Mappings({
        @Mapping(source = "typeId", target = "type.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    Evaluation toEvaluation(EvaluationDTO dto);

    List<EvaluationDTO> toEvaluationDTOList(List<Evaluation> entities);
    List<Evaluation> toEvaluationList(List<EvaluationDTO> dtos);

    default Page<EvaluationDTO> toEvaluationDTOPage(Page<Evaluation> entities) {
        return new PageImpl<>(toEvaluationDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<Evaluation> toEvaluationPage(Page<EvaluationDTO> dtos) {
        return new PageImpl<>(toEvaluationList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== EVALUATION ANSWER =====================
    @Mappings({
        @Mapping(source = "evaluation.id", target = "evaluationId"),
        @Mapping(source = "question.id", target = "questionId")
    })
    EvaluationAnswerDTO toEvaluationAnswerDTO(EvaluationAnswer entity);

    @Mappings({
        @Mapping(source = "evaluationId", target = "evaluation.id"),
        @Mapping(source = "questionId", target = "question.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    EvaluationAnswer toEvaluationAnswer(EvaluationAnswerDTO dto);

    List<EvaluationAnswerDTO> toEvaluationAnswerDTOList(List<EvaluationAnswer> entities);
    List<EvaluationAnswer> toEvaluationAnswerList(List<EvaluationAnswerDTO> dtos);

    default Page<EvaluationAnswerDTO> toEvaluationAnswerDTOPage(Page<EvaluationAnswer> entities) {
        return new PageImpl<>(toEvaluationAnswerDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<EvaluationAnswer> toEvaluationAnswerPage(Page<EvaluationAnswerDTO> dtos) {
        return new PageImpl<>(toEvaluationAnswerList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== EVALUATION FILE =====================
    @Mappings({
        @Mapping(source = "evaluation.id", target = "evaluationId")
    })
    EvaluationFileDTO toEvaluationFileDTO(EvaluationFile entity);

    @Mappings({
        @Mapping(source = "evaluationId", target = "evaluation.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    EvaluationFile toEvaluationFile(EvaluationFileDTO dto);

    List<EvaluationFileDTO> toEvaluationFileDTOList(List<EvaluationFile> entities);
    List<EvaluationFile> toEvaluationFileList(List<EvaluationFileDTO> dtos);

    default Page<EvaluationFileDTO> toEvaluationFileDTOPage(Page<EvaluationFile> entities) {
        return new PageImpl<>(toEvaluationFileDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<EvaluationFile> toEvaluationFilePage(Page<EvaluationFileDTO> dtos) {
        return new PageImpl<>(toEvaluationFileList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== EVALUATION QUESTION =====================
    @Mappings({
        @Mapping(source = "category.id", target = "categoryId")
    })
    EvaluationQuestionDTO toEvaluationQuestionDTO(EvaluationQuestion entity);

    @Mappings({
        @Mapping(source = "categoryId", target = "category.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    EvaluationQuestion toEvaluationQuestion(EvaluationQuestionDTO dto);

    List<EvaluationQuestionDTO> toEvaluationQuestionDTOList(List<EvaluationQuestion> entities);
    List<EvaluationQuestion> toEvaluationQuestionList(List<EvaluationQuestionDTO> dtos);

    default Page<EvaluationQuestionDTO> toEvaluationQuestionDTOPage(Page<EvaluationQuestion> entities) {
        return new PageImpl<>(toEvaluationQuestionDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<EvaluationQuestion> toEvaluationQuestionPage(Page<EvaluationQuestionDTO> dtos) {
        return new PageImpl<>(toEvaluationQuestionList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }

    // ===================== QUESTION CATEGORY =====================
    @Mappings({
        @Mapping(source = "type.id", target = "typeId")
    })
    QuestionCategoryDTO toQuestionCategoryDTO(QuestionCategory entity);

    @Mappings({
        @Mapping(source = "typeId", target = "type.id"),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "isActive", ignore = true)
    })
    QuestionCategory toQuestionCategory(QuestionCategoryDTO dto);

    List<QuestionCategoryDTO> toQuestionCategoryDTOList(List<QuestionCategory> entities);
    List<QuestionCategory> toQuestionCategoryList(List<QuestionCategoryDTO> dtos);

    default Page<QuestionCategoryDTO> toQuestionCategoryDTOPage(Page<QuestionCategory> entities) {
        return new PageImpl<>(toQuestionCategoryDTOList(entities.getContent()), entities.getPageable(), entities.getTotalElements());
    }

    default Page<QuestionCategory> toQuestionCategoryPage(Page<QuestionCategoryDTO> dtos) {
        return new PageImpl<>(toQuestionCategoryList(dtos.getContent()), dtos.getPageable(), dtos.getTotalElements());
    }
}