package com.example.taskmanagerbackend.kanban_item;

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

import io.micrometer.common.lang.NonNull;

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

    @GetMapping("/column/{columnId}")
    public List<KanbanItem> getKanbanItemsByColumnId(@PathVariable("columnId") Long columnId) {
        return kanbanItemService.getKanbanItemsByColumnId(columnId);
    }

    @PostMapping
    public KanbanItem addNewKanbanItem(@RequestBody KanbanItem kanbanItem) {
        return kanbanItemService.addNewKanbanItem(kanbanItem);
    }

    @DeleteMapping(path = "{kanbanItemId}")
    public void deleteKanbanItem(@PathVariable Long kanbanItemId) {
        kanbanItemService.deleteKanbanItem(kanbanItemId);
    }

    @PutMapping(path = "{kanbanItemId}")
    public KanbanItem updateKanbanItem(
            @PathVariable("taskId") @NonNull Long kanbanItemId,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) Date date) {
        return kanbanItemService.updateKanbanItem(kanbanItemId, value, date);
    }
}
