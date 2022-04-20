package com.prometheus.prometheusbackend.controller;

import java.util.concurrent.ExecutionException;

import com.prometheus.prometheusbackend.entity.User;
import com.prometheus.prometheusbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/users")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return userService.createProduct(user);
    }

    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email) throws InterruptedException, ExecutionException{
        return userService.getUserDetails(email);
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return userService.updateProduct(user);
    }

    
    
}
