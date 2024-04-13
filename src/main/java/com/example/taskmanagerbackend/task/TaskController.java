package com.example.taskmanagerbackend.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "/sorted")
    public List<Task> getTasksSortedByPosition() {
        return taskService.getTasksSortedByPosition();
    }

    @PostMapping
    public Task addNewTask(@RequestBody Task task) {
        return taskService.addNewTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping(path = "{taskId}")
    public Task renameTask(
            @PathVariable("taskId") Long taskId,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Date date) {
        return taskService.updateTask(taskId, value, date);
    }

    @PutMapping(path = "/finished/{taskId}")
    public Task setFinished(@PathVariable("taskId") Long taskId,
                            @RequestParam(required = true) boolean isFinished) {
        return taskService.setFinished(taskId, isFinished);
    }

    @PutMapping(path = "/position/{taskId}")
    public Task setPosition(@PathVariable("taskId") Long taskId,
                            @RequestParam(required = true) int position) {
        return taskService.setPosition(taskId, position);
    }

}
