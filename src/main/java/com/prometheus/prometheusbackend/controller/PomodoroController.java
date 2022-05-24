package com.prometheus.prometheusbackend.controller;

import java.util.concurrent.ExecutionException;

import com.prometheus.prometheusbackend.entity.Pomodoro;
import com.prometheus.prometheusbackend.service.PomodoroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PomodoroController {
    
    @Autowired
    private PomodoroService pomodoroService;

    @CrossOrigin(origins = "*")
    @PostMapping("/pomodoros/{task_id}")
    public String createPomodoro(@RequestBody Pomodoro pomodoro, @PathVariable String task_id) throws InterruptedException, ExecutionException{
        return pomodoroService.createPomodoro(pomodoro, task_id);
    }

}
