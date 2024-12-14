package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.TaskDto;
import com.fullStack.project.todolist.models.Entity.Task;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.utils.Enum.StatusEnum;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.Enum.UrgencyEnum;
import com.fullStack.project.todolist.repository.Interfaces.ITaskRepository;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.TaskValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Supplier;

@Validated
@Service
public class TaskService implements ITaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<TaskDto> findAll() {

        return UtilityMethods.safeFindAll(taskRepository, "task", TaskConverter::fromEntity, logger);

    }

    @Override
    public TaskDto findById(Long taskId) {

        return UtilityMethods.safeFindById(taskId, "task", taskRepository, TaskConverter::fromEntity, logger);

    }

    @Override
    public List<TaskDto> findByUrgencyLevel(UrgencyEnum urgencyLevel) {
        return findTasksWithFilter(
                () -> taskRepository.findByUrgencyLevel(urgencyLevel),
                "Urgency level: " + urgencyLevel
        );
    }

    @Override
    public List<TaskDto> findByStatus(StatusEnum status) {
        return findTasksWithFilter(
                () -> taskRepository.findByStatus(status),
                "Status: " + status
        );
    }

    @Override
    public List<TaskDto> findByEstimation(Integer estimationTime) {
        return findTasksWithFilter(
                () -> taskRepository.findByEstimation(estimationTime),
                "Estimation time: " + estimationTime
        );
    }

    @Override
    public List<TaskDto> findByCreationDate(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByCreationDate(date),
                "Creation date: " + date
        );
    }

    @Override
    public List<TaskDto> findByCreationDateAfter(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByCreationDateAfter(date),
                "Creation date after: " + date
        );
    }

    @Override
    public List<TaskDto> findByCreationDateBefore(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByCreationDateBefore(date),
                "Creation date before: " + date
        );
    }

    @Override
    public List<TaskDto> findByStartDate(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByStartDate(date),
                "Start date: " + date
        );
    }

    @Override
    public List<TaskDto> findByStartDateAfter(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByStartDateAfter(date),
                "Start date after: " + date
        );
    }

    @Override
    public List<TaskDto> findByStartDateBefore(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByStartDateBefore(date),
                "Start date before: " + date
        );
    }

    @Override
    public List<TaskDto> findByEndDate(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByEndDate(date),
                "End date: " + date
        );
    }

    @Override
    public List<TaskDto> findByEndDateAfter(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByEndDateAfter(date),
                "End date after: " + date
        );
    }

    @Override
    public List<TaskDto> findByEndDateBefore(Timestamp date) {
        return findTasksWithFilter(
                () -> taskRepository.findByEndDateBefore(date),
                "End date before: " + date
        );
    }

    @Override
    public TaskDto findByObjective(String objective) {
        logger.info("Fetching task with objective: {}", objective);
        return taskRepository.findByObjective(objective)
                .map(TaskConverter::fromEntity)
                .orElseThrow(() -> {
                    logger.error("Task with objective '{}' not found", objective);
                    return new EntityNotFoundException("Task with objective " + objective + " not found.");
                });
    }

    @Override
    public TaskDto create(TaskDto taskDto) {

        logger.info("Creating a new task...");

        List<String> errors = TaskValidator.validate(taskDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::info);
            throw new InvalidEntityException("Invalid task", errors);
        }

        validateTaskUniqueness(taskDto);

        Task taskToSave = TaskConverter.toEntity(taskDto);

        TaskDto savedTask = UtilityMethods.safeSave(taskToSave, taskRepository, TaskConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedTask.getId(), "setTaskId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("Task successfully created with ID: {}", savedTask.getId());

        return savedTask;
    }

    @Override
    public TaskDto update(Long taskId, TaskDto taskDto) {

        logger.info("Updating task with ID: {}", taskId);

        List<String> errors = TaskValidator.validate(taskDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::info);
            throw new InvalidEntityException("Invalid task", errors);
        }

        validateTaskUniqueness(taskDto);

        Task taskToUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> {
                    logger.error("Task with ID {} not found!", taskId);
                    return new EntityNotFoundException("Task with ID " + taskId + " not found.");
                });

        taskToUpdate.setObjective(taskDto.getObjective());

        TaskDto updatedTask = UtilityMethods.safeSave(taskToUpdate, taskRepository, TaskConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(updatedTask.getId(), "setTaskId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        logger.info("Task with ID {} successfully updated", taskId);

        return updatedTask;
    }

    @Override
    public void delete(Long taskId) {
        logger.info("Deleting task with ID: {}", taskId);

        if (taskId == null) {
            logger.error("Task ID cannot be null");
            throw new BadRequestException("Cannot delete task with null ID");
        }

        Task taskToDelete = taskRepository.findById(taskId)
                .orElseThrow(() -> {
                    logger.error("Task with ID {} not found", taskId);
                    return new EntityNotFoundException("Task with ID " + taskId + " not found.");
                });

        UtilityMethods.safeDelete(taskToDelete, taskRepository, logger);

        logger.info("Task with ID {} successfully deleted", taskId);
    }

    private void validateTaskUniqueness(TaskDto taskDto) {
        if (taskRepository.findByObjective(taskDto.getObjective()).isPresent()) {
            logger.error("Task with objective '{}' already exists", taskDto.getObjective());
            throw new BadRequestException("Task with objective '" + taskDto.getObjective() + "' already exists.");
        }
    }

    private List<TaskDto> findTasksWithFilter(Supplier<List<Task>> supplier, String filterDescription) {

        logger.info("Fetching tasks with filter: {}", filterDescription);

        List<TaskDto> tasks = supplier.get()
                .stream()
                .map(TaskConverter::fromEntity)
                .toList();

        logger.info("Found {} tasks for filter: {}", tasks.size(), filterDescription);

        return tasks;
    }
}
