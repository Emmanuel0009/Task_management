package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.EstimationDto;
import com.fullStack.project.todolist.service.Interfaces.IEstimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estimation")
public class EstimationController {

    @Autowired
    private IEstimationService estimationService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<EstimationDto>> getAll( ) {
        return ResponseEntity.ok(estimationService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EstimationDto> getById (@PathVariable("id") Long estimationId) {
        return ResponseEntity.ok(estimationService.findById(estimationId));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<EstimationDto> create (@RequestBody EstimationDto estimation) {
        return ResponseEntity.ok(estimationService.create(estimation));
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<EstimationDto> update (@PathVariable("id") Long estimationId, @RequestBody EstimationDto estimation) {
        return  ResponseEntity.ok(estimationService.update(estimationId, estimation));
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete (@PathVariable("id") Long estimationId) {
        estimationService.delete(estimationId);
        return ResponseEntity.noContent().build();
    }
}

