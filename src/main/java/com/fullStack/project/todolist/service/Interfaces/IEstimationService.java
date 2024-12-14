package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.EstimationDto;

import java.util.List;

public interface IEstimationService {
    List<EstimationDto> findAll ();
    EstimationDto findById(Long estimationId);
    EstimationDto create (EstimationDto estimation);
    EstimationDto update (Long estimationId, EstimationDto estimation);
    void delete (Long estimationId);
}
