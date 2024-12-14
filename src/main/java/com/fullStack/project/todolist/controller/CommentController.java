package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.CommentDto;
import com.fullStack.project.todolist.service.Interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<CommentDto>> getAll( ) {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CommentDto> getById (@PathVariable("id") Long commentId) {
        return ResponseEntity.ok(commentService.findById(commentId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CommentDto> create (@RequestBody CommentDto comment) {
        return ResponseEntity.ok(commentService.create(comment));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<CommentDto> update (@PathVariable("id") Long commentId, @RequestBody CommentDto comment) {
        return  ResponseEntity.ok(commentService.update(commentId,comment));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }
}
