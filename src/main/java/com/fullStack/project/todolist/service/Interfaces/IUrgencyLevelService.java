package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.UrgencyLevelDto;

import java.util.List;

public interface IUrgencyLevelService {
    List<UrgencyLevelDto> findAll ();
    UrgencyLevelDto findById (Long urgencyLevelId);
    UrgencyLevelDto create (UrgencyLevelDto status);
    UrgencyLevelDto update (Long urgencyLevelId, UrgencyLevelDto status);
    void delete (Long urgencyLevelId);
}
