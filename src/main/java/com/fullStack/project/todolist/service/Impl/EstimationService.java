package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.EstimationConverter;
import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.EstimationDto;
import com.fullStack.project.todolist.models.Entity.Estimation;
import com.fullStack.project.todolist.repository.Interfaces.IEstimationRepository;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.service.Interfaces.IEstimationService;
import com.fullStack.project.todolist.service.Interfaces.ITaskService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.EstimationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimationService implements IEstimationService {

    private static final Logger logger = LoggerFactory.getLogger(EstimationService.class);

    @Autowired
    private IEstimationRepository estimationRepository;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<EstimationDto> findAll() {

        return UtilityMethods.safeFindAll(estimationRepository, "estimation", EstimationConverter::fromEntity, logger);

    }

    @Override
    public EstimationDto findById(Long estimationId) {

        return UtilityMethods.safeFindById(estimationId, "estimation", estimationRepository, EstimationConverter::fromEntity, logger);

    }

    @Override
    public EstimationDto create(EstimationDto estimationDto) {

        logger.info("Creating estimation...");

        List<String> errors = EstimationValidator.validate(estimationDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (estimationRepository.findById(estimationDto.getId()).isPresent()) {
            logger.error("Estimation already exists with ID: {}", estimationDto.getId());
            throw new BadRequestException("Estimation already exists with this ID");
        }

        Estimation estimationToSave = EstimationConverter.toEntity(estimationDto);

        estimationToSave.setTask(TaskConverter.toEntity(taskService.findById(estimationDto.getTaskId())));

        EstimationDto savedEstimation = UtilityMethods.safeSave(estimationToSave,estimationRepository, EstimationConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedEstimation.getId(), "setEstimationId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("Estimation successfully created with ID: {}", savedEstimation.getId());

        return savedEstimation;
    }

    @Override
    public EstimationDto update(Long estimationId, EstimationDto estimationDto) {

        logger.info("Updating estimation with ID: {}", estimationId);

        List<String> errors = EstimationValidator.validate(estimationDto);

        if (estimationId == null) {
            logger.error("Cannot update estimation with null ID");
            throw new BadRequestException("Cannot update estimation with null ID");
        }

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        Estimation estimationToUpdate = estimationRepository.findById(estimationId).orElseThrow(
                () -> {
                    logger.error("Estimation with ID {} does not exist", estimationId);
                    return new EntityNotFoundException("Estimation with ID " + estimationId + " does not exist");
                }
        );

        estimationToUpdate.setEstimationTime(estimationDto.getEstimationTime());

        EstimationDto updatedEstimation = UtilityMethods.safeSave(estimationToUpdate, estimationRepository, EstimationConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(estimationId, "setEstimationId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        logger.info("Estimation successfully updated with ID: {}", updatedEstimation.getId());

        return updatedEstimation;
    }

    @Override
    public void delete(Long estimationId) {

        logger.info("Deleting estimation with ID: {}", estimationId);

        if (estimationId == null) {
            logger.error("Cannot delete estimation with null ID");
            throw new BadRequestException("Cannot delete estimation with null ID");
        }

        Estimation estimationToDelete = estimationRepository.findById(estimationId).orElseThrow(
                () -> {
                    logger.error("Estimation with ID {} does not exist.", estimationId);
                    return new EntityNotFoundException("Estimation with ID " + estimationId + " does not exist");
                }
        );

        UtilityMethods.safeDelete(estimationToDelete, estimationRepository, logger);

        logger.info("Estimation successfully deleted with ID: {}", estimationId);
    }
}
