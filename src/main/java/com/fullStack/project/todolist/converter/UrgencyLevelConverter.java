package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.UrgencyLevelDto;
import com.fullStack.project.todolist.models.Entity.UrgencyLevel;

public class UrgencyLevelConverter {

    public static UrgencyLevel toEntity (UrgencyLevelDto urgencyLevelDto) {

        if (urgencyLevelDto == null) {
            return null;
            // TODO Throw an Error
        }

        UrgencyLevel urgencyLevel = new UrgencyLevel();

        urgencyLevel.setId(urgencyLevelDto.getId());
        urgencyLevel.setUrgencyLevel(urgencyLevelDto.getUrgencyLevel());

        return urgencyLevel;
    }

    public static UrgencyLevelDto fromEntity (UrgencyLevel urgencyLevel) {

        if (urgencyLevel == null) {
            return  null;
            // TODO Throw an Error
        }

        UrgencyLevelDto urgencyLevelDto = new UrgencyLevelDto();

        urgencyLevelDto.setId(urgencyLevel.getId());
        urgencyLevelDto.setUrgencyLevel(urgencyLevel.getUrgencyLevel());
        urgencyLevelDto.setTaskId(urgencyLevel.getTask().getId());

        return urgencyLevelDto;
    }

}
