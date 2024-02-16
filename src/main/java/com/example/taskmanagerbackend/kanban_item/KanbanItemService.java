package com.example.taskmanagerbackend.kanban_item;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class KanbanItemService {

    private final KanbanItemRepository kanbanItemRepository;

    public KanbanItemService(KanbanItemRepository kanbanItemRepository) {
        this.kanbanItemRepository = kanbanItemRepository;
    }

    public List<KanbanItem> getKanbanItems() {
        return kanbanItemRepository.findAll();
    }



}
