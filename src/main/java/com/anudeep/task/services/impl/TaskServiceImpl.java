package com.anudeep.task.services.impl;

import com.anudeep.task.domain.entities.Task;
import com.anudeep.task.domain.entities.TaskList;
import com.anudeep.task.domain.entities.TaskPrority;
import com.anudeep.task.domain.entities.TaskStatus;
import com.anudeep.task.mappers.TaskMapper;
import com.anudeep.task.repositories.TaskListRepository;
import com.anudeep.task.repositories.TaskRepository;
import com.anudeep.task.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId()) {
            throw new IllegalArgumentException("Task already has an id");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title!");
        }

        TaskPrority taskPrority = Optional.ofNullable(task.getPrority()).orElse(TaskPrority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List Id"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPrority,
                taskList,
                now,
                now
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (null == task.getId()) {
            throw new IllegalArgumentException("Task must have an id");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Task Ids do not match");
        }
        if(null == task.getPrority()) {
            throw new IllegalArgumentException("Task must have a valid priority");
        }
        if(null == task.getStatus()) {
            throw new IllegalArgumentException("Task must have a valid status");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPrority(task.getPrority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
