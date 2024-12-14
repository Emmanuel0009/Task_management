package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.StatusDto;
import com.fullStack.project.todolist.service.Interfaces.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/status")
public class StatusController {

    @Autowired
    private IStatusService statusService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<StatusDto>> getAll( ) {
        return ResponseEntity.ok(statusService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StatusDto> getById (@PathVariable("id") Long statusId) {
        return ResponseEntity.ok(statusService.findById(statusId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<StatusDto> create (@RequestBody StatusDto status) {
        return ResponseEntity.ok(statusService.create(status));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<StatusDto> update (@PathVariable("id") Long statusId, @RequestBody StatusDto status) {
        return  ResponseEntity.ok(statusService.update(statusId, status));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long statusId) {
        statusService.delete(statusId);
        return ResponseEntity.noContent().build();
    }
}

