package com.fullStack.project.todolist.converter;

import com.fullStack.project.todolist.models.DTO.TaskDto;
import com.fullStack.project.todolist.models.Entity.Task;

public class TaskConverter {

    public static Task toEntity (TaskDto taskDto) {

        Task task = new Task();

        task.setId(taskDto.getId());
        task.setObjective(taskDto.getObjective());

        return task;
    }

    public static TaskDto fromEntity (Task task) {

        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setObjective(task.getObjective());

        return taskDto;
    }

}
