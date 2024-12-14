package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.ExampleDto;
import com.fullStack.project.todolist.service.Interfaces.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/example")
public class ExampleController {

    @Autowired
    private IExampleService exampleService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ExampleDto>> getAll( ) {
        return ResponseEntity.ok(exampleService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ExampleDto> getById (@PathVariable("id") Long exampleId) {
        return ResponseEntity.ok(exampleService.findById(exampleId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<ExampleDto> create (@RequestBody ExampleDto example) {
        return ResponseEntity.ok(exampleService.create(example));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<ExampleDto> update (@PathVariable("id") Long exampleId, @RequestBody ExampleDto example) {
        return  ResponseEntity.ok(exampleService.update(exampleId, example));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long exampleId) {
        exampleService.delete(exampleId);
        return ResponseEntity.noContent().build();
    }
}

