package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.ExampleConverter;
import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.ExampleDto;
import com.fullStack.project.todolist.models.Entity.Example;
import com.fullStack.project.todolist.repository.Interfaces.IExampleRepository;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.service.Interfaces.IExampleService;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.ExampleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService implements IExampleService {

    private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);

    @Autowired
    private IExampleRepository exampleRepository;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<ExampleDto> findAll() {

        return UtilityMethods.safeFindAll(exampleRepository, "example", ExampleConverter::fromEntity, logger);

    }

    @Override
    public ExampleDto findById(Long exampleId) {

        return UtilityMethods.safeFindById(exampleId, "example", exampleRepository, ExampleConverter::fromEntity, logger);

    }

    @Override
    public ExampleDto create(ExampleDto exampleDto) {

        logger.info("Creating example...");

        List<String> errors = ExampleValidator.validate(exampleDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (exampleRepository.findById(exampleDto.getId()).isPresent()) {
            logger.error("Example already exists with ID: {}", exampleDto.getId());
            throw new BadRequestException("Example already exists with this ID");
        }

        Example exampleToSave = ExampleConverter.toEntity(exampleDto);

        exampleToSave.setTask(TaskConverter.toEntity(taskService.findById(exampleDto.getTaskId())));

        ExampleDto savedExample = UtilityMethods.safeSave(exampleToSave, exampleRepository, ExampleConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedExample.getId(), "setExampleId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("Example successfully created with ID: {}", savedExample.getId());

        return savedExample;
    }

    @Override
    public ExampleDto update(Long exampleId, ExampleDto exampleDto) {

        logger.info("Updating example with ID: {}", exampleId);

        List<String> errors = ExampleValidator.validate(exampleDto);

        if (exampleId == null) {
            logger.error("Cannot update example with null ID");
            throw new BadRequestException("Cannot update example with null ID");
        }

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        Example exampleToUpdate = exampleRepository.findById(exampleId).orElseThrow(
                () -> {
                    logger.error("Example with ID {} does not exist", exampleId);
                    return new EntityNotFoundException("Example with ID " + exampleId + " does not exist");
                }
        );

        exampleToUpdate.setExampleText(exampleDto.getExampleText());

        ExampleDto updatedExample = UtilityMethods.safeSave(exampleToUpdate, exampleRepository, ExampleConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(updatedExample.getId(), "setExampleId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        logger.info("Example successfully updated with ID: {}", updatedExample.getId());

        return updatedExample;
    }

    @Override
    public void delete(Long exampleId) {

        logger.info("Deleting example with ID: {}", exampleId);

        if (exampleId == null) {
            logger.error("Cannot delete example with null ID");
            throw new BadRequestException("Cannot delete example with null ID");
        }

        Example exampleToDelete = exampleRepository.findById(exampleId).orElseThrow(
                () -> {
                    logger.error("Example with ID {} does not exist.", exampleId);
                    return new EntityNotFoundException("Example with ID " + exampleId + " does not exist");
                }
        );

        UtilityMethods.safeDelete(exampleToDelete, exampleRepository, logger);

        logger.info("Example successfully deleted with ID: {}", exampleId);
    }
}
