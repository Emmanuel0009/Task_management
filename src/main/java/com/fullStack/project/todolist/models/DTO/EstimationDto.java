package com.fullStack.project.todolist.models.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class EstimationDto {

    private Long id;

    private Integer estimationTime;

    private Long taskId;

}
