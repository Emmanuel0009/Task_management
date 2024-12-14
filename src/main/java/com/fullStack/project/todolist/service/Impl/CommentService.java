package com.fullStack.project.todolist.service.Impl;

import com.fullStack.project.todolist.converter.CommentConverter;
import com.fullStack.project.todolist.converter.TaskConverter;
import com.fullStack.project.todolist.exceptions.*;
import com.fullStack.project.todolist.models.DTO.CommentDto;
import com.fullStack.project.todolist.models.Entity.Comment;
import com.fullStack.project.todolist.repository.Interfaces.ICommentRepository;
import com.fullStack.project.todolist.repository.Interfaces.ITaskRepository;
import com.fullStack.project.todolist.service.Interfaces.ICommentService;
import com.fullStack.project.todolist.service.Interfaces.ICustomDateService;
import com.fullStack.project.todolist.utils.Enum.TypeDateEnum;
import com.fullStack.project.todolist.utils.methodes.UtilityMethods;
import com.fullStack.project.todolist.validators.CommentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private ICustomDateService customDateService;

    @Override
    public List<CommentDto> findAll() {

        return UtilityMethods.safeFindAll(commentRepository, "comment", CommentConverter::fromEntity, logger);

    }

    @Override
    public CommentDto findById(Long commentDtoId) {

        return UtilityMethods.safeFindById(commentDtoId, "comment", commentRepository, CommentConverter::fromEntity, logger);

    }

    @Override
    public CommentDto create(CommentDto commentDto) {

        logger.info("Creating a new comment...");

        List<String> errors = CommentValidator.validate(commentDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (commentRepository.findById(commentDto.getId()).isPresent()) {
            logger.error("Comment already exists with ID: {}", commentDto.getId());
            throw new BadRequestException("Comment already exists with this ID");
        }

        Comment commentToSave = CommentConverter.toEntity(commentDto);

        commentToSave.setTask(TaskConverter.toEntity(UtilityMethods.safeFindById(commentDto.getId(), "task", taskRepository, TaskConverter::fromEntity, logger)));

        CommentDto savedComment = UtilityMethods.safeSave(commentToSave, commentRepository, CommentConverter::fromEntity, logger);

        UtilityMethods.safeCreateCustomDate(savedComment.getId(), "setCommentId", TypeDateEnum.CREATION_DATE, customDateService, logger);

        logger.info("Comment successfully created with ID: {}", savedComment.getId());

        return savedComment;
    }

    @Override
    public CommentDto update(Long commentId, CommentDto commentDto) {

        logger.info("Updating comment with ID: {}", commentId);

        List<String> errors = CommentValidator.validate(commentDto);

        if (!errors.isEmpty()) {
            errors.forEach(logger::error);
            throw new InvalidEntityException("invalid comment", errors);
        }

        if (commentId == null) {
            logger.error("Cannot update comment with null ID");
            throw new BadRequestException("Cannot update comment with null ID");
        }

        CommentDto commentToUpdate = UtilityMethods.safeFindById(commentId, "comment", commentRepository, CommentConverter::fromEntity, logger);

        commentToUpdate.setCommentText(commentDto.getCommentText());

        UtilityMethods.safeCreateCustomDate(commentId, "setCommentId", TypeDateEnum.MODIFIED_DATE, customDateService, logger);

        CommentDto savedComment = UtilityMethods.safeSave(CommentConverter.toEntity(commentToUpdate), commentRepository, CommentConverter::fromEntity, logger);

        logger.info("Comment successfully updated with ID: {}", savedComment.getId());

        return savedComment;
    }

    @Override
    public void delete(Long commentId) {

        logger.info("Deleting comment with ID: {}", commentId);

        if (commentId == null) {
            logger.error("Cannot delete comment with null ID");
            throw new BadRequestException("Cannot delete comment with null ID");
        }

        CommentDto commentToDelete = UtilityMethods.safeFindById(commentId, "comment", commentRepository, CommentConverter::fromEntity, logger);

        UtilityMethods.safeDelete(CommentConverter.toEntity(commentToDelete), commentRepository, logger);

        logger.info("Comment successfully deleted with ID: {}", commentId);
    }
}
