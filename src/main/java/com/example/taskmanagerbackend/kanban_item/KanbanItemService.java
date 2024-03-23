package com.example.taskmanagerbackend.kanban_item;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class KanbanItemService {

    private final KanbanItemRepository kanbanItemRepository;

    public KanbanItemService(KanbanItemRepository kanbanItemRepository) {
        this.kanbanItemRepository = kanbanItemRepository;
    }

    public List<KanbanItem> getKanbanItems() {
        return kanbanItemRepository.findAll();
    }

    public List<KanbanItem> getKanbanItemsByColumnId(Long columnId) {
        return kanbanItemRepository.findByColumnIdOrderByPositionAsc(columnId);
    }

    public KanbanItem addNewKanbanItem(KanbanItem kanbanItem) {
        if (kanbanItem == null) {
            throw new IllegalArgumentException("kanbanItem must not be null");
        }
        return kanbanItemRepository.save(kanbanItem);
    }

    public void deleteKanbanItem(Long kanbanItemId) {
        if (kanbanItemId == null) {
            throw new IllegalArgumentException("kanbanItemId must not be null");
        }
        boolean exists = kanbanItemRepository.existsById(kanbanItemId);
        if (!exists) {
            throw new IllegalStateException("KanbanItem with id " + kanbanItemId + "does not exists");
        }
        kanbanItemRepository.deleteById(kanbanItemId);
    }

    @Transactional
    public KanbanItem updateKanbanItem(Long kanbanItemId, String value, Date date) {
        if (kanbanItemId == null) {
            throw new IllegalArgumentException("kanbanItemId must not be null");
        }
        KanbanItem kanbanItem = kanbanItemRepository.findById(kanbanItemId)
                .orElseThrow(() -> new IllegalStateException("KanbanItem with id " + kanbanItemId + "does not exist"));

        if (value != null && !Objects.equals(kanbanItem.getValue(), value)) {
            kanbanItem.setValue(value);
        }

        if (date != null && !Objects.equals(kanbanItem.getDate(), date)) {
            kanbanItem.setDate(date);
        }

        return kanbanItem;
    }

}
