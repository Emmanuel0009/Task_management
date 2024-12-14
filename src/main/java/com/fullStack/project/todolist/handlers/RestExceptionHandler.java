package com.fullStack.project.todolist.handlers;

import com.fullStack.project.todolist.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException (EntityNotFoundException exception, WebRequest request) {

        final HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;

        final ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode(exception.getErrorCode());
        errorDto.setHttpCode(notFoundStatus.value());
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, notFoundStatus);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleException (BadRequestException exception, WebRequest request) {

        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode(exception.getErrorCode());
        errorDto.setHttpCode(badRequestStatus.value());
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, badRequestStatus);
    }

    @ExceptionHandler(SavingErrorException.class)
    public ResponseEntity<ErrorDto> handleException (SavingErrorException exception, WebRequest request) {

        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode(exception.getErrorCode());
        errorDto.setHttpCode(badRequestStatus.value());
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, badRequestStatus);
    }

    @ExceptionHandler(DeletingErrorException.class)
    public ResponseEntity<ErrorDto> handleException (DeletingErrorException exception, WebRequest request) {

        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode(exception.getErrorCode());
        errorDto.setHttpCode(badRequestStatus.value());
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, badRequestStatus);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException (InvalidEntityException exception, WebRequest request) {

        final HttpStatus invalidStatus = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        errorDto.setErrors(exception.getErrors());
        errorDto.setHttpCode(invalidStatus.value());
        errorDto.setErrorCode(exception.getErrorCode());

        return new ResponseEntity<>(errorDto, invalidStatus);
    }
}
