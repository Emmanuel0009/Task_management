package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.DescriptionDto;

import java.util.List;

public interface IDescriptionService {
    List<DescriptionDto> findAll ();
    DescriptionDto findById (Long descriptionId);
    DescriptionDto create (DescriptionDto description);
    DescriptionDto update (Long descriptionId, DescriptionDto description);
    void delete (Long descriptionId);
}
