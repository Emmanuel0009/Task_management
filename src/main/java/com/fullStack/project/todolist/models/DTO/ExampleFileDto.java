package com.fullStack.project.todolist.models.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ExampleFileDto {

    private Long id;

    private Byte[] file;

    private Long exampleId;

}
