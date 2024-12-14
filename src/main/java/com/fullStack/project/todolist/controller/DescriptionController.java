package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.DescriptionDto;
import com.fullStack.project.todolist.service.Interfaces.IDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/description")
public class DescriptionController {

    @Autowired
    private IDescriptionService descriptionService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<DescriptionDto>> getAll( ) {
        return ResponseEntity.ok(descriptionService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<DescriptionDto> getById (@PathVariable("id") Long descriptionId) {
        return ResponseEntity.ok(descriptionService.findById(descriptionId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<DescriptionDto> create (@RequestBody DescriptionDto description) {
        return ResponseEntity.ok(descriptionService.create(description));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<DescriptionDto> update (@PathVariable("id") Long descriptionId, @RequestBody DescriptionDto description) {
        return  ResponseEntity.ok(descriptionService.update(descriptionId, description));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long descriptionId) {
        descriptionService.delete(descriptionId);
        return ResponseEntity.noContent().build();
    }
}

