package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.UrgencyLevelConverter;
import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.UrgencyLevelDto;
import com.fullStack.project.todolist.models.Entity.UrgencyLevel;
import com.fullStack.project.todolist.repository.Interfaces.IUrgencyLevelRepository;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.service.Interfaces.IUrgencyLevelService;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.UrgencyLevelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrgencyLevelService implements IUrgencyLevelService {

    private static final Logger logger = LoggerFactory.getLogger(UrgencyLevelService.class);

    @Autowired
    private IUrgencyLevelRepository urgencyLevelRepository;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<UrgencyLevelDto> findAll() {

        return UtilityMethods.safeFindAll(urgencyLevelRepository, "urgency level", UrgencyLevelConverter::fromEntity, logger);

    }

    @Override
    public UrgencyLevelDto findById(Long urgencyLevelId) {

        return UtilityMethods.safeFindById(urgencyLevelId, "urgency level", urgencyLevelRepository, UrgencyLevelConverter::fromEntity, logger);

    }

    @Override
    public UrgencyLevelDto create(UrgencyLevelDto urgencyLevelDto) {

        logger.info("Creating urgencyLevel...");

        List<String> errors = UrgencyLevelValidator.validate(urgencyLevelDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (urgencyLevelRepository.findById(urgencyLevelDto.getId()).isPresent()) {
            logger.error("UrgencyLevel already exists with ID: {}", urgencyLevelDto.getId());
            throw new BadRequestException("UrgencyLevel already exists with this ID");
        }

        UrgencyLevel urgencyLevelToSave = UrgencyLevelConverter.toEntity(urgencyLevelDto);

        urgencyLevelToSave.setTask(TaskConverter.toEntity(taskService.findById(urgencyLevelDto.getTaskId())));

        UrgencyLevelDto savedUrgencyLevel = UtilityMethods.safeSave(urgencyLevelToSave, urgencyLevelRepository, UrgencyLevelConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedUrgencyLevel.getId(), "setUrgencyLevel", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("UrgencyLevel successfully created with ID: {}", savedUrgencyLevel.getId());

        return savedUrgencyLevel;
    }

    @Override
    public UrgencyLevelDto update(Long urgencyLevelId, UrgencyLevelDto urgencyLevelDto) {

        logger.info("Updating urgencyLevel with ID: {}", urgencyLevelId);

        List<String> errors = UrgencyLevelValidator.validate(urgencyLevelDto);

        if (urgencyLevelId == null) {
            logger.error("Cannot update urgencyLevel with null ID");
            throw new BadRequestException("Cannot update urgencyLevel with null ID");
        }

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        UrgencyLevel urgencyLevelToUpdate = urgencyLevelRepository.findById(urgencyLevelId).orElseThrow(
                () -> {
                    logger.error("UrgencyLevel with ID {} does not exist", urgencyLevelId);
                    return new EntityNotFoundException("UrgencyLevel with ID " + urgencyLevelId + " does not exist");
                }
        );

        urgencyLevelToUpdate.setUrgencyLevel(urgencyLevelDto.getUrgencyLevel());

        UrgencyLevelDto updatedUrgencyLevel = UtilityMethods.safeSave(urgencyLevelToUpdate, urgencyLevelRepository, UrgencyLevelConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(updatedUrgencyLevel.getId(), "setUrgencyLevelId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        logger.info("UrgencyLevel successfully updated with ID: {}", updatedUrgencyLevel.getId());

        return updatedUrgencyLevel;
    }

    @Override
    public void delete(Long urgencyLevelId) {

        logger.info("Deleting urgencyLevel with ID: {}", urgencyLevelId);

        if (urgencyLevelId == null) {
            logger.error("Cannot delete urgencyLevel with null ID");
            throw new BadRequestException("Cannot delete urgencyLevel with null ID");
        }

        UrgencyLevel urgencyLevelToDelete = urgencyLevelRepository.findById(urgencyLevelId).orElseThrow(
                () -> {
                    logger.error("UrgencyLevel with ID {} does not exist.", urgencyLevelId);
                    return new EntityNotFoundException("UrgencyLevel with ID " + urgencyLevelId + " does not exist");
                }
        );

        UtilityMethods.safeDelete(urgencyLevelToDelete, urgencyLevelRepository, logger);

        logger.info("UrgencyLevel successfully deleted with ID: {}", urgencyLevelId);
    }
}
