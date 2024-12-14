package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.ExampleFileDto;

import java.util.ArrayList;
import java.util.List;

public class ExampleFileValidator {

    public static List<String> validate (ExampleFileDto exampleFileDto) {

        List<String> errors = new ArrayList<>();

        if (exampleFileDto == null) {
            errors.add("Invalid exampleFileDto: exampleFileDto must not be null");
            return errors;
        }

        if (exampleFileDto.getFile() == null) {
            errors.add("Invalid exampleFileDto: exampleFileDto must have a file (getFile is null)");
        }

        if (exampleFileDto.getExampleId() == null) {
            errors.add("ExampleFileDto is attached with no example (exampleId is null)");
        }

        return errors;

    }

}
