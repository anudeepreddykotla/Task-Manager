package com.anudeep.task.domain.dto;

import com.anudeep.task.domain.entities.TaskPrority;
import com.anudeep.task.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPrority prority,
        TaskStatus status
) {
}
