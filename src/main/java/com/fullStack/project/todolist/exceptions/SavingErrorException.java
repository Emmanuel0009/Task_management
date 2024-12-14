package com.fullStack.project.todolist.exceptions;

import lombok.Getter;

@Getter
public class SavingErrorException extends RuntimeException{

    private ErrorCodes errorCode;

    public SavingErrorException (String message) {
        super(message);
    }

    public SavingErrorException (String message, Throwable cause) {
        super(message, cause);
    }

    public SavingErrorException (String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SavingErrorException (String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
