package com.example.taskmanagerbackend.kanban_item;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class KanbanItem {

    @Id
    @SequenceGenerator(name = "kanban_item_sequence", sequenceName = "kanban_item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kanban_item_sequence")
    private long id;

    private long columnId;
    private String value;
    private Date date;

    public KanbanItem() {
    }

    public KanbanItem(long columnId, String value, Date date) {
        this.columnId = columnId;
        this.value = value;
        this.date = date;
    }

    public KanbanItem(long columnId, String value) {
        this.columnId = columnId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getColumnId() {
        return columnId;
    }

    public void setColumnId(long columnId) {
        this.columnId = columnId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "KanbanItem [id=]" + id
                + ", columnId=" + columnId
                + ", value=" + value
                + ", date=" + date;
    }

}
