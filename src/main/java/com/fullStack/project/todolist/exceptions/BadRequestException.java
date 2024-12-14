package com.fullStack.project.todolist.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private ErrorCodes errorCode;

    public BadRequestException (String message) {
        super(message);
    }

    public BadRequestException (String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException (String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BadRequestException (String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
