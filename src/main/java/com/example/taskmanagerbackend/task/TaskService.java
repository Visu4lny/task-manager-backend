package com.example.taskmanagerbackend.task;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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

    public void addNewTask(Task task) {
       taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if (!exists) {
            throw new IllegalStateException("Task with id " + taskId + " does not exists");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public void updateTask(Long taskId, String value, Date date) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exists"));

        if (value != null &&
            !Objects.equals(task.getValue(), value)) {
            task.setValue(value);
        }

        if (date != null &&
            !Objects.equals(task.getDate(), date)) {
            task.setDate(date);
        }

    }

    @Transactional
    public void setFinished(Long taskId, boolean isFinished) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exists"));

        if (!Objects.equals(task.isFinished(), isFinished)) {
            task.setFinished(isFinished);
        }
    }

 }
