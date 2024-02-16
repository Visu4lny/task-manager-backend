package com.example.taskmanagerbackend.kanban_item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/kanban")
public class KanbanItemController {

    private final KanbanItemService kanbanItemService;

    @Autowired
    public KanbanItemController(KanbanItemService kanbanItemService) {
        this.kanbanItemService = kanbanItemService;
    }

    @GetMapping
    public List<KanbanItem> getKanbanItems() {
        return kanbanItemService.getKanbanItems();
    }
}
