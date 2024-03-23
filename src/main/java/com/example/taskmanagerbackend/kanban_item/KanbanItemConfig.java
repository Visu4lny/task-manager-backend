package com.example.taskmanagerbackend.kanban_item;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KanbanItemConfig {

    @SuppressWarnings("null")
    @Bean("kanbanCommandLineRunner")
    CommandLineRunner commandLineRunner(KanbanItemRepository repository) {
        return args -> {
            KanbanItem item1 = new KanbanItem(
                    1,
                    "Item1",
                    Date.valueOf("2023-09-30"),
                    1);

            KanbanItem item2 = new KanbanItem(
                    1,
                    "Item2",
                    Date.valueOf("2023-06-12"),
                    0);

            KanbanItem item3 = new KanbanItem(
                    1,
                    "Item2",
                    Date.valueOf("2023-06-12"),
                    2);

            KanbanItem item4 = new KanbanItem(
                    2,
                    "Item2",
                    Date.valueOf("2023-06-12"),
                    0);

            KanbanItem item5 = new KanbanItem(
                    3,
                    "Item2",
                    Date.valueOf("2023-06-12"),
                    0);

            KanbanItem item6 = new KanbanItem(
                    3,
                    "Item2",
                    Date.valueOf("2023-06-12"),
                    1);

            repository.saveAll(List.of(item1, item2, item3, item4, item5, item6));
        };
    }
}