package com.fullStack.project.todolist.models.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class TaskDto {

    private Long id;

    private String objective;

}
