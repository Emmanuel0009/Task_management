package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.DescriptionDto;

import java.util.ArrayList;
import java.util.List;

public class DescriptionValidator {

    public static List<String> validate (DescriptionDto descriptionDto) {

        List<String> errors = new ArrayList<>();

        if (descriptionDto == null) {
            errors.add("Invalid descriptionDto: descriptionDto must not be null");
            return errors;
        }

        if (descriptionDto.getDescriptionText() == null) {
            errors.add("Invalid descriptionDto: descriptionDto has no description text (descriptionText is null)");
        }

        if (descriptionDto.getTaskId() == null) {
            errors.add("DescriptionDto is attached with no task (taskId is null)");
        }

        return errors;

    }
}
