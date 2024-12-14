package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.CommentDto;
import com.fullStack.project.todolist.models.Entity.Comment;

public class CommentConverter {

    public static Comment toEntity (CommentDto commentDto) {

        if (commentDto == null) {
            throw new RuntimeException("Invalid DTO: commentDto is null");
        }

        Comment comment =  new Comment();

        comment.setId(commentDto.getId());
        comment.setCommentText(commentDto.getCommentText());

        return comment;
    }

    public static CommentDto fromEntity (Comment comment) {

        if (comment == null) {
            throw new RuntimeException("Invalid Entity: comment is null");
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setCommentText(comment.getCommentText());
        commentDto.setTaskId(comment.getTask().getId());

        return commentDto;
    }

}
