package com.fullStack.project.todolist.handlers;

import com.fullStack.project.todolist.exceptions.ErrorCodes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorDto {

    private Integer HttpCode;

    private String message;

    private ErrorCodes errorCode;

    private List<String> errors;
}
