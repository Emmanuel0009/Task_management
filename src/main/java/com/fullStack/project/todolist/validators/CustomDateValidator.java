package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.CustomDateDto;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomDateValidator {

    public static List<String> validate (CustomDateDto customDateDto) {

        List<String> errors = new ArrayList<>();

        if (customDateDto == null) {
            errors.add("Invalid customDateDto: customDateDto must not be null");
            return errors;
        }

        List<TypeDateEnum> typeDateEnumList = List.of(
                TypeDateEnum.CREATION_DATE,
                TypeDateEnum.START_DATE,
                TypeDateEnum.CLOSING_DATE,
                TypeDateEnum.REOPENING_DATE,
                TypeDateEnum.END_DATE,
                TypeDateEnum.MODIFIED_DATE
        );

        List<Long> dateFieldIds= Arrays.asList(
                customDateDto.getCommentId(),
                customDateDto.getEstimationId(),
                customDateDto.getExampleFileId(),
                customDateDto.getUrgencyLevelId(),
                customDateDto.getDescriptionId(),
                customDateDto.getStatusId(),
                customDateDto.getExampleId(),
                customDateDto.getTaskId()
        );

        if (dateFieldIds.stream().filter(Objects::nonNull).count() != 1) {
            errors.add("Invalid customDateDto: it must be associated with exactly one element.");
        }

        if (customDateDto.getDate() == null) {
            errors.add("Invalid customDateDto: customDateDto has no date");
        }

        if (customDateDto.getTypeDate() == null || !typeDateEnumList.contains(customDateDto.getTypeDate())) {
            errors.add("Invalid customDateDto: typeDate is null or invalid");
        }

        return errors;

    }
}
