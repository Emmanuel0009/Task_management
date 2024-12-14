package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.DescriptionDto;
import com.fullStack.project.todolist.models.Entity.Description;

public class DescriptionConverter {

    public static Description toEntity (DescriptionDto descriptionDto) {

        if(descriptionDto == null) {
            return null;
            // TODO Throw an Error
        }

        Description description = new Description();

        description.setId(descriptionDto.getId());
        description.setDescriptionText(descriptionDto.getDescriptionText());

        return description;
    }

    public static DescriptionDto fromEntity (Description description) {

        if(description == null) {
            return null;
            // TODO Throw an Error
        }

        DescriptionDto descriptionDto = new DescriptionDto();

        descriptionDto.setId(description.getId());
        descriptionDto.setDescriptionText(description.getDescriptionText());
        descriptionDto.setTaskId(description.getTask().getId());

        return descriptionDto;
    }
}
