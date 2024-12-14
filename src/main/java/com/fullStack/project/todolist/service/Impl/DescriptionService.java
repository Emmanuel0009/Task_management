package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.DescriptionConverter;
import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.DescriptionDto;
import com.fullStack.project.todolist.models.Entity.Description;
import com.fullStack.project.todolist.repository.Interfaces.IDescriptionRepository;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.service.Interfaces.IDescriptionService;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.DescriptionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionService implements IDescriptionService {

    private static final Logger logger = LoggerFactory.getLogger(DescriptionService.class);

    @Autowired
    private IDescriptionRepository descriptionRepository;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<DescriptionDto> findAll() {

        return UtilityMethods.safeFindAll(descriptionRepository, "description", DescriptionConverter::fromEntity, logger);

    }

    @Override
    public DescriptionDto findById(Long descriptionId) {

        return UtilityMethods.safeFindById(descriptionId, "description", descriptionRepository, DescriptionConverter::fromEntity, logger);

    }

    @Override
    public DescriptionDto create(DescriptionDto descriptionDto) {

        logger.info("Creating description...");

        List<String> errors = DescriptionValidator.validate(descriptionDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (descriptionRepository.findById(descriptionDto.getId()).isPresent()) {
            logger.error("Description already exists with ID: {}", descriptionDto.getId());
            throw new BadRequestException("Description already exists with this ID");
        }

        Description descriptionToSave = DescriptionConverter.toEntity(descriptionDto);

        descriptionToSave.setTask(TaskConverter.toEntity(taskService.findById(descriptionDto.getTaskId())));

        DescriptionDto savedDescription = UtilityMethods.safeSave(descriptionToSave, descriptionRepository, DescriptionConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedDescription.getId(), "setDescriptionId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("Description successfully created with ID: {}", savedDescription.getId());

        return savedDescription;
    }

    @Override
    public DescriptionDto update(Long descriptionId, DescriptionDto descriptionDto) {

        logger.info("Updating description with ID: {}", descriptionId);

        List<String> errors = DescriptionValidator.validate(descriptionDto);

        if (descriptionId == null) {
            logger.error("Cannot update description with null ID");
            throw new BadRequestException("Cannot update description with null ID");
        }

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        DescriptionDto descriptionToUpdate = UtilityMethods.safeFindById(descriptionId, "description", descriptionRepository, DescriptionConverter::fromEntity, logger);

        descriptionToUpdate.setDescriptionText(descriptionDto.getDescriptionText());

        UtilityMethods.safeCreateCustomDate(descriptionId, "setDescriptionId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        DescriptionDto updatedDescription = UtilityMethods.safeSave(DescriptionConverter.toEntity(descriptionToUpdate), descriptionRepository, DescriptionConverter::fromEntity, logger);

        logger.info("Description successfully updated with ID: {}", updatedDescription.getId());

        return updatedDescription;
    }

    @Override
    public void delete(Long descriptionId) {

        logger.info("Deleting description with ID: {}", descriptionId);

        if (descriptionId == null) {
            logger.error("Cannot delete description with null ID");
            throw new BadRequestException("Cannot delete description with null ID");
        }

        DescriptionDto descriptionToDelete = UtilityMethods.safeFindById(descriptionId, "description", descriptionRepository, DescriptionConverter::fromEntity, logger);

        UtilityMethods.safeDelete(DescriptionConverter.toEntity(descriptionToDelete), descriptionRepository, logger);

        logger.info("Description successfully deleted with ID: {}", descriptionId);
    }
}
