package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.StatusDto;
import com.fullStack.project.todolist.models.Entity.Status;

public class StatusConverter {

    public static Status toEntity (StatusDto statusDto) {

        if (statusDto == null) {
            return null;
            // TODO Throw an Error
        }

        Status status = new Status();

        status.setId(statusDto.getId());
        status.setStatus(statusDto.getStatus());

        return status;
    }

    public static StatusDto fromEntity (Status status) {

        if (status == null) {
            return null;
            // TODO Throw an Error
        }

        StatusDto statusDto = new StatusDto();

        statusDto.setId(status.getId());
        statusDto.setStatus(status.getStatus());
        statusDto.setTaskId(status.getTask().getId());

        return statusDto;
    }
}
