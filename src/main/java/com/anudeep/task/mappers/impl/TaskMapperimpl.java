package com.anudeep.task.mappers.impl;

import com.anudeep.task.domain.dto.TaskDto;
import com.anudeep.task.domain.entities.Task;
import com.anudeep.task.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperimpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.prority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPrority(),
                task.getStatus()
        );
    }
}
