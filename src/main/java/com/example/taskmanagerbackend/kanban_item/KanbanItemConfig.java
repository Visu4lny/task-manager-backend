package com.example.taskmanagerbackend.kanban_item;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KanbanItemConfig {

    @Bean("kanbanCommandLineRunner")
    CommandLineRunner commandLineRunner(KanbanItemRepository repository) {
        return args -> {
            KanbanItem item1 = new KanbanItem(0, "Item1");

            Date date = new Date(0);
            date = date.valueOf("2024-01-20");
            KanbanItem item2 = new KanbanItem(
                    1,
                    "Item2",
                    date);

            repository.saveAll(List.of(item1, item2));
        };
    }
}