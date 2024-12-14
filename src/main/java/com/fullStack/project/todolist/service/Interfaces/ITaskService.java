package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.TaskDto;
import com.fullStack.project.todolist.utils.Enum.StatusEnum;
import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;

import java.sql.Timestamp;
import java.util.List;

public interface ITaskService {
    List<TaskDto> findAll();
    List<TaskDto> findByUrgencyLevel (UrgencyEnum urgencyLevel);
    List<TaskDto> findByStatus (StatusEnum status);
    List<TaskDto> findByEstimation (Integer estimationTime);
    List<TaskDto> findByCreationDate (Timestamp date);
    List<TaskDto> findByCreationDateAfter (Timestamp date);
    List<TaskDto> findByCreationDateBefore (Timestamp date);
    List<TaskDto> findByStartDate (Timestamp date);
    List<TaskDto> findByStartDateAfter (Timestamp date);
    List<TaskDto> findByStartDateBefore (Timestamp date);
    List<TaskDto> findByEndDate (Timestamp date);
    List<TaskDto> findByEndDateAfter (Timestamp date);
    List<TaskDto> findByEndDateBefore (Timestamp date);
    TaskDto findById(Long taskId);
    TaskDto findByObjective (String objective);
    TaskDto create (TaskDto task);
    TaskDto update (Long taskId, TaskDto task);
    void delete (Long taskId);
}
