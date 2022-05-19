package com.prometheus.prometheusbackend.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.prometheus.prometheusbackend.entity.Task;
import com.prometheus.prometheusbackend.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @CrossOrigin(origins = "*")
    @PostMapping("/tasks")
    public String createTask(@RequestBody Task task) throws InterruptedException, ExecutionException{
        return taskService.createTask(task);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/tasks/{task_id}")
    public Task getTask(@PathVariable String task_id) throws InterruptedException, ExecutionException{
        return taskService.getTaskDetails(task_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/tasks")
    public List<Task> getAllTasks() throws InterruptedException, ExecutionException{
        return taskService.getAllTaskDetails();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/tasksPerUser/{user_id}")
    public List<Task> getAllTasksPerUser(@PathVariable String user_id) throws InterruptedException, ExecutionException{
        return taskService.getAllTasksPerUser(user_id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/tasks")
    public String updateTask(@RequestBody Task task) throws InterruptedException, ExecutionException{
        return taskService.updateTask(task);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/tasks/{task_id}")
    public String deleteUser(@PathVariable String task_id) throws InterruptedException, ExecutionException{
        return taskService.deleteTask(task_id);
    }


    
}
