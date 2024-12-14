package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.ExampleDto;

import java.util.ArrayList;
import java.util.List;

public class ExampleValidator {

    public static List<String> validate (ExampleDto exampleDto) {

        List<String> errors = new ArrayList<>();

        if (exampleDto == null) {
            errors.add("Invalid exampleDto: exampleDto must not be null");
            return errors;
        }

        if (exampleDto.getTaskId() == null) {
            errors.add("ExampleDto must be attached with a task (taskId is null)");
        }

        return errors;
    }
}
