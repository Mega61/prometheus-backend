package com.prometheus.prometheusbackend.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.prometheus.prometheusbackend.entity.User;
import com.prometheus.prometheusbackend.service.UserService;

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
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping("/users")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return userService.createUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{client_id}")
    public User getUser(@PathVariable String client_id) throws InterruptedException, ExecutionException{
        return userService.getUserDetails(client_id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    public List<User> getAllUsers() throws InterruptedException, ExecutionException{
        return userService.getAllUserDetails();
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/users")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return userService.updateUser(user);
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{client_id}")
    public String deleteUser(@PathVariable String client_id) throws ExecutionException, InterruptedException{
        return userService.deleteUser(client_id);
    } 

    
    
}
