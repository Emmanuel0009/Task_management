package com.fullStack.project.todolist.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCodes {

    COMMENT_NOT_FOUND(100),
    COMMENT_NOT_VALID(110),
    CUSTOM_DATE_NOT_FOUND(200),
    CUSTOM_DATE_NOT_VALID(210),
    DESCRIPTION_NOT_FOUND(300),
    DESCRIPTION_NOT_VALID(310),
    ESTIMATION_NOT_FOUND(400),
    ESTIMATION_NOT_VALID(410),
    EXAMPLE_NOT_FOUND(500),
    EXAMPLE_NOT_VALID(510),
    EXAMPLE_FILE_NOT_FOUND(600),
    EXAMPLE_FILE_NOT_VALID(610),
    STATUS_NOT_FOUND(700),
    STATUS_NOT_VALID(710),
    TASK_NOT_FOUND(800),
    TASK_NOT_VALID(810),
    URGENCY_LEVEL_NOT_FOUND(900),
    URGENCY_LEVEL_NOT_VALID(910);

    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }
}
