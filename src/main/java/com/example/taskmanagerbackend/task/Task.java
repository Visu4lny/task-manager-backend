package com.example.taskmanagerbackend.task;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Task {

    @Id
    @SequenceGenerator(
                    name = "task_sequence",
                    sequenceName = "task_sequence",
                    allocationSize = 1
    )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "task_sequence"
    )
    private Long id;

    private String value;
    private boolean isFinished;
    private Date date;

    public Task() {
    }

    public Task(String value, boolean isFinished, Date date) {
        this.value = value;
        this.isFinished = isFinished;
        this.date = date;
    }

    public Task(String value, boolean isFinished) {
        this.value = value;
        this.isFinished = isFinished;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", value=" + value + ", isFinished=" + isFinished + ", date=" + date + "]";
    }
}
