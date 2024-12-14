package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.ExampleFileDto;
import com.fullStack.project.todolist.models.Entity.ExampleFile;

public class ExampleFileConverter {

    public static ExampleFile toEntity (ExampleFileDto exampleFileDto) {

        if (exampleFileDto == null) {
            return null;
            // TODO Throw an Error
        }

        ExampleFile exampleFile = new ExampleFile();

        exampleFile.setId(exampleFileDto.getId());
        exampleFile.setFile(exampleFileDto.getFile());

        return exampleFile;
    }

    public static ExampleFileDto fromEntity (ExampleFile exampleFile) {

        if (exampleFile == null) {
            return null;
            // TODO Throw an Error
        }

        ExampleFileDto exampleFileDto = new ExampleFileDto ();

        exampleFileDto.setId(exampleFile.getId());
        exampleFileDto.setFile(exampleFile.getFile());
        exampleFileDto.setExampleId(exampleFile.getExample().getId());

        return  exampleFileDto;
    }
}
