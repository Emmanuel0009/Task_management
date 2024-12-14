package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.CustomDateDto;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;

import java.util.List;

public interface ICustomDateService {
    List<CustomDateDto> findAll ();
    List<CustomDateDto> findByTaskId (Long taskId);
    List<CustomDateDto> findByTaskIdAndTypeDate (Long taskId, TypeDateEnum typeDate);
    List<CustomDateDto> findByCommentId (Long commentId);
    List<CustomDateDto> findByCommentIdAndTypeDate (Long commentId, TypeDateEnum typeDate);
    List<CustomDateDto> findByDescriptionId (Long descriptionId);
    List<CustomDateDto> findByDescriptionIdAndTypeDate (Long descriptionId, TypeDateEnum typeDate);
    List<CustomDateDto> findByEstimationId (Long estimationId);
    List<CustomDateDto> findByEstimationIdAndTypeDate  (Long estimationId, TypeDateEnum typeDate);
    List<CustomDateDto> findByExampleId (Long exampleId);
    List<CustomDateDto> findByExampleIdAndTypeDate (Long exampleId,TypeDateEnum typeDate);
    List<CustomDateDto> findByExampleFileId (Long exampleFileId);
    List<CustomDateDto> findByExampleFileIdAndTypeDate (Long exampleFileId, TypeDateEnum typeDate);
    List<CustomDateDto> findByStatusId (Long statusId);
    List<CustomDateDto> findByStatusIdAndTypeDate (Long statusId, TypeDateEnum typeDate);
    List<CustomDateDto> findByUrgencyLevelId (Long urgencyLevelId);
    List<CustomDateDto> findByUrgencyLevelIdAndTypeDate (Long urgencyLevelId, TypeDateEnum typeDate);
    CustomDateDto findById (Long commentDtoId);
    CustomDateDto create (CustomDateDto commentDto);
    CustomDateDto update (Long commentId, CustomDateDto commentDto);
    void delete (Long commentDtoId);
}
