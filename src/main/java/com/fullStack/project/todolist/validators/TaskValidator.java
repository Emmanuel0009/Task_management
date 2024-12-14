package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class TaskValidator {

    public static List<String> validate(TaskDto taskDto) {

        List<String> errors = new ArrayList<>();

        if (taskDto == null) {
            errors.add("taskDto must not be null");
            return errors;
        }

        if (taskDto.getObjective() == null) {
            errors.add("objective of the task with ID: " + taskDto.getId() + "must not be null");
        }

        return errors;
    }
}
