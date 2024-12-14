package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.StatusDto;
import com.fullStack.project.todolist.utils.Enum.StatusEnum;

import java.util.ArrayList;
import java.util.List;

public class StatusValidator {

    public static List<String> validate (StatusDto statusDto) {

        List<String> errors = new ArrayList<>();

        if (statusDto == null) {
            errors.add("Invalid statusDto: statusDto must no be null");
            return errors;
        }

        if (statusDto.getStatus() == null) {
            errors.add("Invalid statusDto: status is null");
        }

        List<StatusEnum> statusEnumList = List.of(
                StatusEnum.TODO,
                StatusEnum.DONE,
                StatusEnum.IN_PROGRESS,
                StatusEnum.PUT_ON_HOLD,
                StatusEnum.ABANDONED,
                StatusEnum.CANCELLED,
                StatusEnum.BLOCKED,
                StatusEnum.REVIEW_PENDING
        );

        if (!statusEnumList.contains(statusDto.getStatus())) {
            errors.add("Invalid statusDto: invalid status");
        }

        if (statusDto.getTaskId() == null) {
            errors.add("StatusDto must be attached with an task (taskId null)");
        }

        return errors;

    }
}
