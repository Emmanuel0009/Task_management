package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.CustomDateDto;
import com.fullStack.project.todolist.models.Entity.*;

import java.util.function.Consumer;
import java.util.function.Function;

public class CustomDateConverter {

    public static CustomDate toEntity(CustomDateDto customDateDto) {

        CustomDate customDate = new CustomDate();

        customDate.setId(customDateDto.getId());
        customDate.setDate(DateConverter.stringToDate(customDateDto.getDate()));
        customDate.setTypeDate(customDateDto.getTypeDate());

        return customDate;
    }

    public static CustomDateDto fromEntity(CustomDate customDate) {

        CustomDateDto customDateDto = new CustomDateDto();

        customDateDto.setId(customDate.getId());
        customDateDto.setTypeDate(customDate.getTypeDate());

        safeSet(customDate.getTask(), Task::getId, customDateDto::setTaskId);
        safeSet(customDate.getComment(), Comment::getId, customDateDto::setCommentId);
        safeSet(customDate.getDescription(), Description::getId, customDateDto::setDescriptionId);
        safeSet(customDate.getEstimation(), Estimation::getId, customDateDto::setEstimationId);
        safeSet(customDate.getExample(), Example::getId, customDateDto::setExampleId);
        safeSet(customDate.getExampleFile(), ExampleFile::getId, customDateDto::setExampleFileId);
        safeSet(customDate.getStatus(), Status::getId, customDateDto::setStatusId);
        safeSet(customDate.getUrgencyLevel(), UrgencyLevel::getId, customDateDto::setUrgencyLevelId);
        safeSet(customDate.getDate(), DateConverter::dateToString, customDateDto::setDate);

        return customDateDto;
    }

    /**
     * Méthode utilitaire pour définir des valeurs de manière sécurisée
     * en évitant les NullPointerException.
     *
     * @param source L'objet source (peut être null).
     * @param getter Fonction pour récupérer une valeur depuis l'objet source.
     * @param setter Consommateur pour définir la valeur dans l'objet cible.
     * @param <T>    Type de l'objet source.
     * @param <R>    Type de la valeur à récupérer.
     */
    private static <T, R> void safeSet(T source, Function<T, R> getter, Consumer<R> setter) {
        if (source != null) {
            R value = getter.apply(source);
            if (value != null) {
                setter.accept(value);
            }
        }
    }
}
