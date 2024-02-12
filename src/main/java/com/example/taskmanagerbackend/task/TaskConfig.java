package com.example.taskmanagerbackend.task;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository) {
        return args -> {
            Task task1 = new Task(
                     "Test1",
                     false,
                     new Date(2015_02_01)
            );

            Task task2 = new Task(
                     "Test2",
                     false,
                     new Date(2020-05-01)
            );

            repository.saveAll(List.of(task1, task2));

        };
    }
}
