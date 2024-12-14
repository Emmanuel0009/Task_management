package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.ExampleDto;

import java.util.List;

public interface IExampleService {
    List<ExampleDto> findAll ();
    ExampleDto findById (Long exampleId);
    ExampleDto create (ExampleDto example);
    ExampleDto update (Long exampleId, ExampleDto example);
    void delete (Long exampleId);
}
