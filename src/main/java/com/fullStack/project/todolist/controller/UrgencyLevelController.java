package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.UrgencyLevelDto;
import com.fullStack.project.todolist.service.Interfaces.IUrgencyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/urgencyLevel")
public class UrgencyLevelController {

    @Autowired
    private IUrgencyLevelService urgencyLevelService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<UrgencyLevelDto>> getAll( ) {
        return ResponseEntity.ok(urgencyLevelService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UrgencyLevelDto> getById (@PathVariable("id") Long urgencyLevelId) {
        return ResponseEntity.ok(urgencyLevelService.findById(urgencyLevelId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<UrgencyLevelDto> create (@RequestBody UrgencyLevelDto urgencyLevel) {
        return ResponseEntity.ok(urgencyLevelService.create(urgencyLevel));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<UrgencyLevelDto> update (@PathVariable("id") Long urgencyLevelId, @RequestBody UrgencyLevelDto urgencyLevel) {
        return  ResponseEntity.ok(urgencyLevelService.update(urgencyLevelId, urgencyLevel));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long urgencyLevelId) {
        urgencyLevelService.delete(urgencyLevelId);
        return ResponseEntity.noContent().build();
    }
}

