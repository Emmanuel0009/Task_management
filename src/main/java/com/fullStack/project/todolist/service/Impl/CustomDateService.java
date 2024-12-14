package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.CustomDateConverter;
import com.fullStack.project.todolist.converter.DateConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.exceptions.SavingErrorException;
import com.fullStack.project.todolist.models.DTO.CustomDateDto;
import com.fullStack.project.todolist.models.Entity.CustomDate;
import com.fullStack.project.todolist.repository.Interfaces.*;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.CustomDateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomDateService implements ICustomDateService {

    private static final Logger logger = LoggerFactory.getLogger(CustomDateService.class);

    @Autowired
    private ICustomDateRepository customDateRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IDescriptionRepository descriptionRepository;

    @Autowired
    private IEstimationRepository estimationRepository;

    @Autowired
    private IExampleRepository exampleRepository;

    @Autowired
    private IExampleFileRepository exampleFileRepository;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private IUrgencyLevelRepository urgencyLevelRepository;

    @Override
    public List<CustomDateDto> findAll() {

        return UtilityMethods.safeFindAll(customDateRepository, "custom date", CustomDateConverter::fromEntity, logger);

    }

    @Override
    public List<CustomDateDto> findByTaskId(Long taskId) {
        logger.info("Start fetching customDate by taskId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByTaskId(taskId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by taskId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by taskId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByTaskIdAndTypeDate(Long taskId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by taskId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByTaskIdAndTypeDate(taskId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by taskId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by taskId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByCommentId(Long commentId) {
        logger.info("Start fetching customDate by commentId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByCommentId(commentId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by commentId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by commentId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByCommentIdAndTypeDate(Long commentId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by commentId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByCommentIdAndTypeDate(commentId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by commentId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by commentId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByDescriptionId(Long descriptionId) {
        logger.info("Start fetching customDate by descriptionId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByDescriptionId(descriptionId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by descriptionId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by descriptionId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByDescriptionIdAndTypeDate(Long descriptionId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by descriptionId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByDescriptionIdAndTypeDate(descriptionId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by descriptionId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by descriptionId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByEstimationId(Long estimationId) {
        logger.info("Start fetching customDate by estimationId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByEstimationId(estimationId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by estimationId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by estimationId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByEstimationIdAndTypeDate(Long estimationId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by estimationId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByEstimationIdAndTypeDate(estimationId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by estimationId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by estimationId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByExampleId(Long exampleId) {
        logger.info("Start fetching customDate by exampleId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByExampleId(exampleId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by exampleId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by exampleId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByExampleIdAndTypeDate(Long exampleId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by exampleId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByExampleIdAndTypeDate(exampleId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by exampleId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by exampleId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByExampleFileId(Long exampleFileId) {
        logger.info("Start fetching customDate by exampleFileId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByExampleFileId(exampleFileId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by exampleFileId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by exampleFileId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByExampleFileIdAndTypeDate(Long exampleFileId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by exampleFileId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByExampleFileIdAndTypeDate(exampleFileId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by exampleFileId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by exampleFileId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByStatusId(Long statusId) {
        logger.info("Start fetching customDate by statusId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByStatusId(statusId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by statusId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by statusId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByStatusIdAndTypeDate(Long statusId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by statusId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByStatusIdAndTypeDate(statusId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by statusId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by statusId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByUrgencyLevelId(Long urgencyLevelId) {
        logger.info("Start fetching customDate by urgencyLevelId");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByUrgencyLevelId(urgencyLevelId).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by urgencyLevelId", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by urgencyLevelId failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<CustomDateDto> findByUrgencyLevelIdAndTypeDate(Long urgencyLevelId, TypeDateEnum typeDate) {
        logger.info("Start fetching customDate by urgencyLevelId and typeDate");
        try {
            List<CustomDateDto> customDates = customDateRepository.findByUrgencyLevelIdAndTypeDate(urgencyLevelId, typeDate).stream()
                    .map(CustomDateConverter::fromEntity)
                    .toList();
            logger.info("Successfully fetching {} customDates by urgencyLevelId and typeDate", customDates.size());
            return customDates;
        } catch (Exception e) {
            logger.error("Fetching customDate by urgencyLevelId and typeDate failed\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public CustomDateDto findById(Long customDateDtoId) {
        logger.info("Fetching customDate with ID: {}", customDateDtoId);
        CustomDateDto customDateDto = CustomDateConverter.fromEntity(
                customDateRepository.findById(customDateDtoId).orElseThrow(
                        () -> {
                            logger.error("CustomDate with ID {} not found.", customDateDtoId);
                            return new EntityNotFoundException("CustomDate with ID " + customDateDtoId + " not found");
                        }
                )
        );
        logger.info("Successfully fetched customDate with ID: {}", customDateDtoId);
        return customDateDto;
    }

    @Override
    public CustomDateDto create(CustomDateDto customDateDto) {

        logger.info("Creating a new customDate...");

        List<String> errors = CustomDateValidator.validate(customDateDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (customDateRepository.findById(customDateDto.getId()).isPresent()) {
            logger.error("CustomDate already exists with ID: {}", customDateDto.getId());
            throw new BadRequestException("CustomDate already exists with this ID");
        }

        CustomDate customDateToSave = CustomDateConverter.toEntity(customDateDto);

        try {
            customDateToSave.setTask(findEntityById(customDateDto.getTaskId(), taskRepository, "Invalid task ID"));
            customDateToSave.setComment(findEntityById(customDateDto.getCommentId(), commentRepository, "Invalid comment ID"));
            customDateToSave.setDescription(findEntityById(customDateDto.getDescriptionId(), descriptionRepository, "Invalid description ID"));
            customDateToSave.setEstimation(findEntityById(customDateDto.getEstimationId(), estimationRepository, "Invalid estimation ID"));
            customDateToSave.setExampleFile(findEntityById(customDateDto.getExampleFileId(), exampleFileRepository, "Invalid exampleFile ID"));
            customDateToSave.setExample(findEntityById(customDateDto.getExampleId(), exampleRepository, "Invalid example ID"));
            customDateToSave.setStatus(findEntityById(customDateDto.getStatusId(), statusRepository, "Invalid status ID"));
            customDateToSave.setUrgencyLevel(findEntityById(customDateDto.getUrgencyLevelId(), urgencyLevelRepository, "Invalid urgencyLevel ID"));
        } catch (Exception e) {
            logger.error("Fetching customDate\n{}", e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
        try {
            CustomDate savedCustomDate = customDateRepository.save(customDateToSave);
            logger.info("CustomDate successfully created with ID: {}", savedCustomDate.getId());
            return CustomDateConverter.fromEntity(savedCustomDate);
        } catch (Exception e) {
            logger.error("creating customDate failed\n{}", e.getMessage());
            throw new SavingErrorException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public CustomDateDto update(Long customDateId, CustomDateDto customDateDto) {

        logger.info("Updating customDate with ID: {}", customDateId);

        List<String> errors = CustomDateValidator.validate(customDateDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (customDateId == null) {
            logger.error("Cannot update customDate with null ID");
            throw new BadRequestException("Cannot update customDate with null ID");
        }

        CustomDate customDateToUpdate = customDateRepository.findById(customDateId).orElseThrow(
                () -> {
                    logger.error("CustomDate with ID {} does not exist.", customDateId);
                    return new EntityNotFoundException("CustomDate with ID " + customDateId + " does not exist");
                }
        );

        customDateToUpdate.setTypeDate(customDateDto.getTypeDate());
        customDateToUpdate.setDate(DateConverter.stringToDate(customDateDto.getDate()));

        try {
            CustomDate updatedCustomDate = customDateRepository.save(customDateToUpdate);
            logger.info("CustomDate successfully updated with ID: {}", updatedCustomDate.getId());
            return CustomDateConverter.fromEntity(updatedCustomDate);
        } catch (Exception e) {
            logger.error("updating customDate failed\n{}", e.getMessage());
            throw new SavingErrorException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long customDateId) {
        logger.info("Deleting customDate with ID: {}", customDateId);

        if (customDateId == null) {
            logger.error("Cannot delete customDate with null ID");
            throw new BadRequestException("Cannot delete customDate with null ID");
        }

        CustomDate customDateToDelete = customDateRepository.findById(customDateId).orElseThrow(
                () -> {
                    logger.error("CustomDate with ID {} does not exist", customDateId);
                    return new EntityNotFoundException("CustomDate with ID " + customDateId + " does not exist");
                }
        );

        try {
            customDateRepository.delete(customDateToDelete);
        } catch (Exception e) {
            logger.error("deleting customDte failed\n{}", e.getMessage());
            throw new SavingErrorException(e.getMessage(), e.getCause());
        }

        logger.info("CustomDate successfully deleted with ID: {}", customDateId);
    }

    private <T> T findEntityById(Long id, JpaRepository<T, Long> repository, String errorMessage) {
        if (id == null || id <= 0) return null;
        return repository.findById(id).orElseThrow(() -> {
            logger.error(errorMessage);
            return new BadRequestException(errorMessage);
        });
    }

}
