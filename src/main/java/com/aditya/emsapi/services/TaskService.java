package com.aditya.emsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.Employee;
import com.aditya.emsapi.model.Task;
import com.aditya.emsapi.repository.EmployeeRepository;
import com.aditya.emsapi.repository.TaskRepository;
import com.aditya.emsapi.view.TaskDTO;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(TaskDTO taskDTO) {
        Employee assignedEmployee = employeeRepository.findById(taskDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setAssignedTo(assignedEmployee);

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        if (taskRepository.existsById(id)) {
            updatedTask.setId(id);
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

