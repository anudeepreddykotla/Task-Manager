package com.anudeep.task.mappers;

import com.anudeep.task.domain.dto.TaskListDto;
import com.anudeep.task.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
