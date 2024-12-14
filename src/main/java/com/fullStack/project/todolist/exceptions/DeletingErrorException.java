package com.fullStack.project.todolist.exceptions;

import lombok.Getter;

@Getter
public class DeletingErrorException extends RuntimeException{

    private ErrorCodes errorCode;

    public DeletingErrorException (String message) {
        super(message);
    }

    public DeletingErrorException (String message, Throwable cause) {
        super(message, cause);
    }

    public DeletingErrorException (String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DeletingErrorException (String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
