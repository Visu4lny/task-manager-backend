package com.example.taskmanagerbackend.task;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @SuppressWarnings("null")
    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository) {
        return args -> {

            Task task1 = new Task(
                    "Test1",
                    false,
                    Date.valueOf("2023-05-10"),
                    1);

            Task task2 = new Task(
                    "Test2",
                    false,
                    Date.valueOf("2023-12-24"),
                    0);

            Task task3 = new Task(
                    "Test3",
                    false,
                    Date.valueOf("2023-12-24"),
                    2);

            repository.saveAll(List.of(task1, task2, task3));
        };
    }
}
