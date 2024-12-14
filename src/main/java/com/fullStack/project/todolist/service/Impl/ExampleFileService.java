package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.ExampleConverter;
import com.fullStack.project.todolist.converter.ExampleFileConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.InvalidEntityException;
import com.fullStack.project.todolist.models.DTO.ExampleFileDto;
import com.fullStack.project.todolist.models.Entity.ExampleFile;
import com.fullStack.project.todolist.repository.Interfaces.IExampleFileRepository;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.service.Interfaces.IExampleFileService;
import com.fullStack.project.todolist.service.Interfaces.IExampleService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.ExampleFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleFileService implements IExampleFileService {

    private static final Logger logger = LoggerFactory.getLogger(ExampleFileService.class);

    @Autowired
    private IExampleFileRepository exampleFileRepository;

    @Autowired
    private IExampleService exampleService;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<ExampleFileDto> findAll() {

        return UtilityMethods.safeFindAll(exampleFileRepository, "example file", ExampleFileConverter::fromEntity, logger);

    }

    @Override
    public ExampleFileDto findById(Long exampleFileId) {

        return UtilityMethods.safeFindById(exampleFileId, "example file", exampleFileRepository, ExampleFileConverter::fromEntity, logger);

    }

    @Override
    public ExampleFileDto create(ExampleFileDto exampleFileDto) {

        logger.info("Creating exampleFile...");

        List<String> errors = ExampleFileValidator.validate(exampleFileDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (exampleFileRepository.findById(exampleFileDto.getId()).isPresent()) {
            logger.error("ExampleFile already exists with ID: {}", exampleFileDto.getId());
            throw new BadRequestException("ExampleFile already exists with this ID");
        }

        ExampleFile exampleFileToSave = ExampleFileConverter.toEntity(exampleFileDto);

        exampleFileToSave.setExample(ExampleConverter.toEntity(exampleService.findById(exampleFileDto.getExampleId())));

        ExampleFileDto savedExampleFile = UtilityMethods.safeSave(exampleFileToSave, exampleFileRepository, ExampleFileConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedExampleFile.getId(), "setExampleFileId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("ExampleFile successfully created with ID: {}", savedExampleFile.getId());

        return savedExampleFile;
    }

    @Override
    public ExampleFileDto update(Long exampleFileId, ExampleFileDto exampleFileDto) {

        logger.info("Updating exampleFile with ID: {}", exampleFileId);

        List<String> errors = ExampleFileValidator.validate(exampleFileDto);

        if (exampleFileId == null) {
            logger.error("Cannot update exampleFile with null ID");
            throw new BadRequestException("Cannot update exampleFile with null ID");
        }

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        ExampleFile exampleFileToUpdate = exampleFileRepository.findById(exampleFileId).orElseThrow(
                () -> {
                    logger.error("ExampleFile with ID {} does not exist", exampleFileId);
                    return new EntityNotFoundException("ExampleFile with ID " + exampleFileId + " does not exist");
                }
        );

        exampleFileToUpdate.setFile(exampleFileDto.getFile());

        ExampleFileDto updatedExampleFile = UtilityMethods.safeSave(exampleFileToUpdate, exampleFileRepository, ExampleFileConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(exampleFileId, "setExampleFileId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        logger.info("ExampleFile successfully updated with ID: {}", updatedExampleFile.getId());

        return updatedExampleFile;
    }

    @Override
    public void delete(Long exampleFileId) {

        logger.info("Deleting exampleFile with ID: {}", exampleFileId);

        if (exampleFileId == null) {
            logger.error("Cannot delete exampleFile with null ID");
            throw new BadRequestException("Cannot delete exampleFile with null ID");
        }

        ExampleFile exampleFileToDelete = exampleFileRepository.findById(exampleFileId).orElseThrow(
                () -> {
                    logger.error("ExampleFile with ID {} does not exist.", exampleFileId);
                    return new EntityNotFoundException("ExampleFile with ID " + exampleFileId + " does not exist");
                }
        );

        UtilityMethods.safeDelete(exampleFileToDelete, exampleFileRepository, logger);

        logger.info("ExampleFile successfully deleted with ID: {}", exampleFileId);
    }
}
