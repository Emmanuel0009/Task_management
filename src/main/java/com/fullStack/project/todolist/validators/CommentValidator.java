package com.fullStack.project.todolist.validators;

import com.fullStack.project.todolist.models.DTO.CommentDto;

import java.util.ArrayList;
import java.util.List;

public class CommentValidator {

    public static List<String> validate (CommentDto commentDto) {

        List<String> errors = new ArrayList<>();

        if (commentDto == null) {
            errors.add("Invalid Dto: commentDto must not be null");
            return errors;
        }

        if (commentDto.getTaskId() == null) {
            errors.add("commentDto is attached with no task (taskId is null)");
        }

        if (commentDto.getCommentText() == null) {
            errors.add("Invalid Dto: commentDto must have a comment text (commentText is null)");
        }

        return errors;
    }
}
