package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.EstimationDto;

import java.util.ArrayList;
import java.util.List;

public class EstimationValidator {

    public static List<String> validate (EstimationDto estimationDto) {

        List<String> errors = new ArrayList<>();

        if (estimationDto == null) {
            errors.add("Invalid estimationDto: estimationDto must not be null");
            return errors;
        }

        if (estimationDto.getEstimationTime() == null) {
            errors.add("Invalid estimationDto: estimationDto must have a estimation time (estimationTime is null)");
        }

        if (estimationDto.getTaskId() == null) {
            errors.add("EstimationDto is attached with no task (taskId is null)");
        }

        return errors;

    }
}
