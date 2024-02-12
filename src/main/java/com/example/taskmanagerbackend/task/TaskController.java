package com.example.taskmanagerbackend.task;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public void addNewTask(@RequestBody Task task) {
        taskService.addNewTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping(path = "{taskId}")
    public void renameTask(
                        @PathVariable("taskId") Long taskId,
                        @RequestParam(required = false) String value,
                        @RequestParam(required = false) Date date
                           ) {
        taskService.updateTask(taskId, value, date);
    }

    @PutMapping(path = "/finished/{taskId}")
    public void setFinished(@PathVariable("taskId") Long taskId,
                            @RequestParam(required = true) boolean isFinished) {
        taskService.setFinished(taskId, isFinished);
    }

}
