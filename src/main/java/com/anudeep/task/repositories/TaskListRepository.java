package com.anudeep.task.repositories;

import com.anudeep.task.domain.dto.TaskListDto;
import com.anudeep.task.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

}
