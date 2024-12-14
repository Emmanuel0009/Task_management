package com.fullStack.project.todolist.models.DTO;

import com.fullStack.project.todolist.utils.Enum.StatusEnum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class StatusDto {

    private Long id;

    private StatusEnum status;

    private Long taskId;

}
