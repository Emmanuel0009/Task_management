package com.fullStack.project.todolist.controller;

import com.fullStack.project.todolist.models.DTO.TaskDto;
import com.fullStack.project.todolist.utils.Enum.StatusEnum;
import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TaskDto> getById(@PathVariable("id") Long taskId) {
        return ResponseEntity.ok(taskService.findById(taskId));
    }

    @GetMapping(value = "/urgency", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByUrgencyLevel (@RequestParam("urgency") UrgencyEnum urgencyEnum) {
        return ResponseEntity.ok(taskService.findByUrgencyLevel(urgencyEnum));
    }

    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByStatus (@RequestParam("status") StatusEnum statusEnum) {
        return ResponseEntity.ok(taskService.findByStatus(statusEnum));
    }

    @GetMapping(value = "/estimation", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByEstimation (@RequestParam("estimation") Integer time) {
        return ResponseEntity.ok(taskService.findByEstimation(time));
    }

    @GetMapping(value = "/creation-date", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByCreationDate (@RequestParam("creationDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByCreationDate(time));
    }

    @GetMapping(value = "/creation-date/before", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByCreationDateBefore (@RequestParam("creationDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByCreationDateBefore(time));
    }

    @GetMapping(value = "/creation-date/after", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByCreationDateAfter (@RequestParam("creationDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByCreationDateAfter(time));
    }

    @GetMapping(value = "/start-date", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByStartDate (@RequestParam("startDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByStartDate(time));
    }

    @GetMapping(value = "/start-date/before", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByStartDateBefore (@RequestParam("startDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByStartDateBefore(time));
    }

    @GetMapping(value = "/start-date/after", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByStartDateAfter (@RequestParam("startDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByStartDateAfter(time));
    }

    @GetMapping(value = "/end-date", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByEndDate (@RequestParam("endDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByEndDate(time));
    }

    @GetMapping(value = "/end-date/before", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByEndDateBefore (@RequestParam("endDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByEndDateBefore(time));
    }

    @GetMapping(value = "/end-date/after", produces = "application/json")
    public ResponseEntity<List<TaskDto>> getByEndDateAfter (@RequestParam("endDate") Timestamp time) {
        return ResponseEntity.ok(taskService.findByEndDateAfter(time));
    }

    @GetMapping(value = "/objective", produces = "application/json")
    public ResponseEntity<TaskDto> getByObjective (@RequestParam("objective") String objective) {
        return ResponseEntity.ok(taskService.findByObjective(objective));
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto task) {
        TaskDto createdTask = taskService.create(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping(value = "/{id}/update", produces = "application/json")
    public ResponseEntity<TaskDto> update(@PathVariable("id") Long taskId, @RequestBody TaskDto task) {
        TaskDto updatedTask = taskService.update(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
