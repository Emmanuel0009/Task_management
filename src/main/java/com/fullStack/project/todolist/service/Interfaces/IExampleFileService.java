package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.ExampleFileDto;

import java.util.List;

public interface IExampleFileService {
    List<ExampleFileDto> findAll ();
    ExampleFileDto findById (Long exampleFileId);
    ExampleFileDto create (ExampleFileDto exampleFile);
    ExampleFileDto update (Long exampleFileId, ExampleFileDto exampleFile);
    void delete (Long exampleFileId);
}
