package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.UrgencyLevelDto;
import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;

import java.util.ArrayList;
import java.util.List;

public class UrgencyLevelValidator {

    public static List<String> validate (UrgencyLevelDto urgencyLevelDto) {

        List<String> errors = new ArrayList<>();

        if (urgencyLevelDto == null) {
            errors.add("Invalid urgencyLevelDto: urgencyLevelDto must not be null");
            return errors;
        }

        if (urgencyLevelDto.getUrgencyLevel() == null) {
            errors.add("Invalid urgencyLevelDto: urgency level is null");
        }

        List<UrgencyEnum> urgencyEnumList = List.of(
                UrgencyEnum.VERY_LOW,
                UrgencyEnum.LOW,
                UrgencyEnum.NORMAL,
                UrgencyEnum.HIGH,
                UrgencyEnum.VERY_HIGH
        );

        if (!urgencyEnumList.contains(urgencyLevelDto.getUrgencyLevel())) {
            errors.add("Invalid urgencyLevelDto: invalid urgency level");
        }

        if (urgencyLevelDto.getTaskId() == null) {
            errors.add("UrgencyLevelDto must be attached with a task (taskId is null)");
        }

        return errors;

    }
}
