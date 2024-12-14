package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.ExampleDto;
import com.fullStack.project.todolist.models.Entity.Example;


public class ExampleConverter {

    public static Example toEntity (ExampleDto exampleDto) {

        if (exampleDto == null) {
            return  null;
            // TODO Throw an Error
        }

        Example example = new Example();

        example.setId(exampleDto.getId());
        example.setExampleText(exampleDto.getExampleText());

        return example;
    }

    public static ExampleDto fromEntity (Example example) {

        if (example == null) {
            return null;
            // TODO Throw an Error
        }

        ExampleDto exampleDto = new ExampleDto();

        exampleDto.setId(example.getId());
        exampleDto.setExampleText(example.getExampleText());
        exampleDto.setTaskId(example.getTask().getId());

        return exampleDto;
    }
}
