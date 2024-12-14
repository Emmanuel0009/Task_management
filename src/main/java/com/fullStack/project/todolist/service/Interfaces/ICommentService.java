package com.fullStack.project.todolist.service.Interfaces;

import com.fullStack.project.todolist.models.DTO.CommentDto;

import java.util.List;

public interface ICommentService {
    List<CommentDto> findAll ();
    CommentDto findById (Long commentDtoId);
    CommentDto create (CommentDto commentDto);
    CommentDto update (Long commentId, CommentDto commentDto);
    void delete (Long commentDtoId);
}
