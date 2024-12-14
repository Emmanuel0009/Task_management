package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.StatusDto;

import java.util.List;

public interface IStatusService {
    List<StatusDto> findAll ();
    StatusDto findById (Long statusId);
    StatusDto create (StatusDto status);
    StatusDto update (Long statusId, StatusDto status);
    void delete (Long statusId);
}
