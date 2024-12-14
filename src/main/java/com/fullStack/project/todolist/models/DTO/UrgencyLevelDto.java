package com.fullStack.project.todolist.models.DTO;

import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UrgencyLevelDto {

    private Long id;

    private UrgencyEnum urgencyLevel;

    private Long taskId;

}
