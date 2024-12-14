package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.ExampleFileDto;
import com.fullStack.project.todolist.service.Interfaces.IExampleFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exampleFile")
public class ExampleFileController {

    @Autowired
    private IExampleFileService exampleFileService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ExampleFileDto>> getAll( ) {
        return ResponseEntity.ok(exampleFileService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ExampleFileDto> getById (@PathVariable("id") Long exampleFileId) {
        return ResponseEntity.ok(exampleFileService.findById(exampleFileId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<ExampleFileDto> create (@RequestBody ExampleFileDto exampleFile) {
        return ResponseEntity.ok(exampleFileService.create(exampleFile));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<ExampleFileDto> update (@PathVariable("id") Long exampleFileId, @RequestBody ExampleFileDto exampleFile) {
        return  ResponseEntity.ok(exampleFileService.update(exampleFileId, exampleFile));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long exampleFileId) {
        exampleFileService.delete(exampleFileId);
        return ResponseEntity.noContent().build();
    }
}
