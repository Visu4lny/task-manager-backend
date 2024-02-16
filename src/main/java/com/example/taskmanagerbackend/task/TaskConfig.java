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

        Date date = new Date(0);

        return args -> {

            Task task1 = new Task(
                    "Test1",
                    false,
                    date.valueOf("2023-05-10"));

            Task task2 = new Task(
                    "Test2",
                    false,
                    date.valueOf("2023-12-24"));

            repository.saveAll(List.of(task1, task2));

        };
    }
}
