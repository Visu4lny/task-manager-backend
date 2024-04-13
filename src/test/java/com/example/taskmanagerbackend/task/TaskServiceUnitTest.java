package com.example.taskmanagerbackend.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService underTest;

    @Test
    void canGetTasks() {
        underTest.getTasks();

        verify(taskRepository).findAll();
    }

    @Test
    void canGetTasksSortedByPosition() {
        underTest.getTasksSortedByPosition();

        Sort sort = Sort.by(Sort.Direction.ASC, "position");
        verify(taskRepository).findAll(sort);
    }

    @Test
    void canAddNewTask() {
        Task task = new Task(
                "Testing test",
                false,
                new Date(2020 - 1 - 20),
                2);

        underTest.addNewTask(task);

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        verify(taskRepository).save(taskArgumentCaptor.capture());

        Task capturedTask = taskArgumentCaptor.getValue();

        assertThat(capturedTask).isEqualTo(task);
    }

    @Test
    void throwsExceptionWhenAddingNewTaskWithNullTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.addNewTask(null);
        });

        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void canDeleteTask() {

        Long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        underTest.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void throwsExceptionWhenDeletingTaskWithNonExistingTask() {
        Long taskId = 2L;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> {
            underTest.deleteTask(taskId);
        });

        verify(taskRepository, never()).deleteById(taskId);
    }

    @Test
    void throwsExceptionWhenDeletingTaskWithNullTaskId() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.deleteTask(null);
        });

        verify(taskRepository, never()).existsById(any());
        verify(taskRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateTask() {
        Task task = new Task("Initial Value", false, new Date(2021 - 3 - 20));

        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskRepository.save(task);

        String newValue = "Updated Value";
        Date newDate = new Date(2024 - 5 - 13);
        Task updatedTask = underTest.updateTask(1L, newValue, newDate);

        verify(taskRepository, times(1)).findById(taskId);

        assertEquals(newValue, updatedTask.getValue());
        assertEquals(newDate, updatedTask.getDate());
    }

    @Test
    void throwsExceptionWhenUpdatingTaskWithNonExistingTask() {
        Long taskId = 2L;

        assertThrows(IllegalStateException.class, () -> {
            underTest.updateTask(taskId, "Updated value", new Date(2023 - 8 - 12));
        });

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void throwsExceptionWhenUpdatingTaskWithNullTaskId() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.updateTask(null, null, null);
        });

        verify(taskRepository, never()).existsById(any());
        verify(taskRepository, never()).findById(any());
    }

    @Test
    void canSetFinished() {
        Task task = new Task("Initial Value", false, new Date(2021 - 3 - 20));

        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskRepository.save(task);

        boolean newIsFinished = true;
        Task updatedTask = underTest.setFinished(1L, newIsFinished);

        verify(taskRepository, times(1)).findById(taskId);

        assertEquals(newIsFinished, updatedTask.isFinished());
    }

    @Test
    void throwsExceptionWhenSettingFinishedWithNonExistingTask() {
        Long taskId = 2L;

        assertThrows(IllegalStateException.class, () -> {
            underTest.setFinished(taskId, true);
        });

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void throwsExceptionWhenSettingFinishedWithNullTaskId() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.setFinished(null, true);
        });

        verify(taskRepository, never()).existsById(any());
        verify(taskRepository, never()).findById(any());
    }

    @Test
    void canSetPosition() {
        Task task = new Task("Initial Value", false, new Date(2021 - 3 - 20));

        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskRepository.save(task);

        int newPosition = 2;
        Task updatedTask = underTest.setPosition(1L, newPosition);

        verify(taskRepository, times(1)).findById(taskId);

        assertEquals(newPosition, updatedTask.getPosition());
    }

    @Test
    void throwsExceptionWhenSettingPositionWithNonExistingTask() {
        Long taskId = 2L;

        assertThrows(IllegalStateException.class, () -> {
            underTest.setPosition(taskId, 2);
        });

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void throwsExceptionWhenSettingPositionWithNullTaskId() {
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.setPosition(null, 2);
        });

        verify(taskRepository, never()).existsById(any());
        verify(taskRepository, never()).findById(any());
    }
}
