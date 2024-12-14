package com.fullStack.project.todolist.utils.methodes;

import com.fullStack.project.todolist.converter.DateConverter;
import com.fullStack.project.todolist.exceptions.BadRequestException;
import com.fullStack.project.todolist.exceptions.DeletingErrorException;
import com.fullStack.project.todolist.exceptions.EntityNotFoundException;
import com.fullStack.project.todolist.exceptions.SavingErrorException;
import com.fullStack.project.todolist.models.DTO.CustomDateDto;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class UtilityMethods {

    public static <T, U> List<U> safeFindAll (JpaRepository<T, Long> repository, String entityName, Function<T,U> converter, Logger logger) {

        logger.info("Fetching all {}s...", entityName);

        try {
            List<U> entitiesFound = repository.findAll().stream().map(converter).toList();
            logger.info("Found successfully {} {}(s)", entitiesFound.size(), entityName);
            return entitiesFound;
        } catch (Exception e) {
            logger.error("Fetching all {}s failed\n{}",entityName, e.getMessage());
            throw new EntityNotFoundException(e.getMessage(), e.getCause());
        }
    }

    public static <T, U> U safeFindById (Long entityId, String entityName, JpaRepository<T, Long> repository, Function<T, U> converter, Logger logger) {

        logger.info("Fetching {} with ID: {}", entityName, entityId);

        if (entityId == null) {
            logger.error("Cannot found {} with null ID", entityName);
            throw new BadRequestException("Provided id for " + entityName + " is null");
        }

        T entity = repository.findById(entityId).orElseThrow(() -> {
            logger.error("{} with ID {} not found.", entityName, entityId);
            return new EntityNotFoundException(entityName + " with ID: " + entityId + " not found");
        });

        return converter.apply(entity);
    }

    public static <T, U> U safeSave (T entityToSave, JpaRepository<T, Long> repository, Function<T, U> converter, Logger logger) {
        try {
            T updatedEntity = repository.save(entityToSave);
            return converter.apply(updatedEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SavingErrorException("Updating entity failed\n" + e.getMessage());
        }
    }

    public static <T> void safeDelete (T entityToDelete, JpaRepository<T, Long> repository, Logger logger) {
        try {
            repository.delete(entityToDelete);
        } catch (Exception e) {
            logger.error("Deleting entity failed\n{}", e.getMessage());
            throw new DeletingErrorException(e.getMessage());
        }
    }


    public static void safeCreateCustomDate (Long entityId, String methode, TypeDateEnum typeDate, ICustomDateService customDateService, Logger logger) {

        logger.info("Creating custom date...");

        if (entityId == null) {
            logger.error("Cannot associate custom date with entity with null ID");
            throw new BadRequestException("Provided id is null");
        }

        try {
            CustomDateDto now = new CustomDateDto();
            now.getClass().getMethod(methode, Long.class).invoke(now, entityId);

            CustomDateDto date = customDateService.create(now);
            logger.info("Custom date successfully created with ID: {}", date.getId());

            now.setTypeDate(typeDate);
            now.setDate(DateConverter.dateToString(Timestamp.valueOf(LocalDateTime.now())));
        } catch (Exception e) {
            if (e instanceof IllegalAccessException || e instanceof InvocationTargetException || e instanceof NoSuchMethodException) {
                logger.error("Creating custom date failed\nReflexion error in the methode safeCreateCustomDate");
                throw new RuntimeException(e);
            }
            logger.error("Saving custom date failed");
            throw new SavingErrorException(e.getMessage(), e.getCause());
        }
    }

}
