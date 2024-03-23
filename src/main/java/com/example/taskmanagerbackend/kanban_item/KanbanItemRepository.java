package com.example.taskmanagerbackend.kanban_item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanbanItemRepository extends JpaRepository<KanbanItem, Long> {

    List<KanbanItem> findByColumnIdOrderByPositionAsc(Long columnId);
}
