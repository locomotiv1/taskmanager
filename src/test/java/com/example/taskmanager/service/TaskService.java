package com.example.taskmanager.service;

import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Tells JUnit to use Mockito
class TaskServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private TaskService taskService;

  private Task mockTask;

  @BeforeEach
  void setUp() {
    mockTask = new Task();
    mockTask.setId(1L);
    mockTask.setTitle("Learn Unit Testing");
    mockTask.setDescription("Write tests for the service layer");
    mockTask.setStatus(TaskStatus.NEW);
  }

  @Test
  void getAllTasks_ShouldReturnListOfTasks() {
    when(taskRepository.findAll()).thenReturn(List.of(mockTask));

    List<Task> result = taskService.getAllTasks();

    assertEquals(1, result.size());
    assertEquals("Learn Unit Testing", result.get(0).getTitle());
  }

  @Test
  void getTaskById_WhenTaskExists_ShouldReturnTask() {
    when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask));

    Task result = taskService.getTaskById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
  }

  @Test
  void getTaskById_WhenTaskDoesNotExist_ShouldThrowException() {
    when(taskRepository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(99L));
  }

  @Test
  void createTask_ShouldReturnSavedTask() {
    when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

    Task result = taskService.createTask(mockTask);

    assertNotNull(result);
    assertEquals("Learn Unit Testing", result.getTitle());
  }
}
