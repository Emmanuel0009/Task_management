package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.EstimationDto;
import com.fullStack.project.todolist.models.Entity.Estimation;

public class EstimationConverter {

    public static Estimation toEntity (EstimationDto estimationDto) {

        if (estimationDto == null) {
            return null;
            // TODO Throw an Error
        }

        Estimation estimation = new Estimation();

        estimation.setId(estimationDto.getId());
        estimation.setEstimationTime(estimationDto.getEstimationTime());

        return estimation;
    }

    public static EstimationDto fromEntity (Estimation estimation) {

        if (estimation == null) {
            return null;
            // TODO Throw an Error
        }

        EstimationDto estimationDto = new EstimationDto();

        estimationDto.setId(estimation.getId());
        estimationDto.setEstimationTime(estimation.getEstimationTime());
        estimationDto.setTaskId(estimation.getTask().getId());

        return estimationDto;
    }
}
