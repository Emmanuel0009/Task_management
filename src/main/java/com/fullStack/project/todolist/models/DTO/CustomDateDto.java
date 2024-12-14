package com.fullStack.project.todolist.models.DTO;

import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CustomDateDto {

    private Long id;

    private String date;

    private Long taskId;

    private TypeDateEnum typeDate;

    private Long commentId;

    private Long descriptionId;

    private Long estimationId;

    private Long exampleId;

    private Long exampleFileId;

    private Long statusId;

    private Long urgencyLevelId;
}
