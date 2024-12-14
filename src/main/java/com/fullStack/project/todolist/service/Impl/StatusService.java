package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.StatusConverter;
import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.StatusDto;
import com.fullStack.project.todolist.models.Entity.Status;
import com.fullStack.project.todolist.repository.Interfaces.IStatusRepository;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.service.Interfaces.IStatusService;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.StatusValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService {

    private static final Logger logger = LoggerFactory.getLogger(StatusService.class);

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<StatusDto> findAll() {

        return UtilityMethods.safeFindAll(statusRepository, "status", StatusConverter::fromEntity, logger);

    }

    @Override
    public StatusDto findById(Long statusId) {

        return UtilityMethods.safeFindById(statusId, "status", statusRepository, StatusConverter::fromEntity, logger);

    }

    @Override
    public StatusDto create(StatusDto statusDto) {

        logger.info("Creating status...");

        List<String> errors = StatusValidator.validate(statusDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (statusRepository.findById(statusDto.getId()).isPresent()) {
            logger.error("Status already exists with ID: {}", statusDto.getId());
            throw new BadRequestException("Status already exists with this ID");
        }

        Status statusToSave = StatusConverter.toEntity(statusDto);

        statusToSave.setTask(TaskConverter.toEntity(taskService.findById(statusDto.getTaskId())));

        StatusDto savedStatus = UtilityMethods.safeSave(statusToSave, statusRepository, StatusConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedStatus.getId(), "setStatusId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("Status successfully created with ID: {}", savedStatus.getId());

        return savedStatus;
    }

    @Override
    public StatusDto update(Long statusId, StatusDto statusDto) {

        logger.info("Updating status with ID: {}", statusId);

        List<String> errors = StatusValidator.validate(statusDto);

        if (statusId == null) {
            logger.error("Cannot update status with null ID");
            throw new BadRequestException("Cannot update status with null ID");
        }

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        Status statusToUpdate = statusRepository.findById(statusId).orElseThrow(
                () -> {
                    logger.error("Status with ID {} does not exist", statusId);
                    return new EntityNotFoundException("Status with ID " + statusId + " does not exist");
                }
        );

        statusToUpdate.setStatus(statusDto.getStatus());

        StatusDto updatedStatus = UtilityMethods.safeSave(statusToUpdate, statusRepository, StatusConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(updatedStatus.getId(), "setStatusId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        logger.info("Status successfully updated with ID: {}", updatedStatus.getId());

        return updatedStatus;
    }

    @Override
    public void delete(Long statusId) {

        logger.info("Deleting status with ID: {}", statusId);

        if (statusId == null) {
            logger.error("Cannot delete status with null ID");
            throw new BadRequestException("Cannot delete status with null ID");
        }

        Status statusToDelete = statusRepository.findById(statusId).orElseThrow(
                () -> {
                    logger.error("Status with ID {} does not exist.", statusId);
                    return new EntityNotFoundException("Status with ID " + statusId + " does not exist");
                }
        );

        UtilityMethods.safeDelete(statusToDelete, statusRepository, logger);

        logger.info("Status successfully deleted with ID: {}", statusId);
    }
}
