package com.example.taskmanagerbackend.task;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksSortedByPosition() {
        Sort sort = Sort.by(Sort.Direction.ASC, "position");

        return taskRepository.findAll(sort);
    }

    public Task addNewTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task must not be null");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("taskId must not be null");
        }
        boolean exists = taskRepository.existsById(taskId);
        if (!exists) {
            throw new IllegalStateException("Task with id " + taskId + " does not exists");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public Task updateTask(Long taskId, String value, Date date) {
        if (taskId == null) {
            throw new IllegalArgumentException("taskId must not be null");
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exist"));

        if (value != null &&
                !Objects.equals(task.getValue(), value)) {
            task.setValue(value);
        }

        if (date != null &&
                !Objects.equals(task.getDate(), date)) {
            task.setDate(date);
        }

        return task;

    }

    @Transactional
    public Task setFinished(Long taskId, boolean isFinished) {
        if (taskId == null) {
            throw new IllegalArgumentException("taskId must not be null");
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exists"));

        if (!Objects.equals(task.isFinished(), isFinished)) {
            task.setFinished(isFinished);
        }

        return task;
    }

    @Transactional
    public Task setPosition(Long taskId, int position) {
        if (taskId == null) {
            throw new IllegalArgumentException("taskId must not be null");
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exists"));

        if (!Objects.equals(task.getPosition(), position)) {
            task.setPosition(position);
        }

        return task;
    }

}
