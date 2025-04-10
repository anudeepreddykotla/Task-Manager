package com.anudeep.task.mappers;

import com.anudeep.task.domain.dto.TaskDto;
import com.anudeep.task.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
