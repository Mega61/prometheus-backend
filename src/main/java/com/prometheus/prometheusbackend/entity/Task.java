package com.prometheus.prometheusbackend.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Task {

    private String task_id;
    private String title;
    private String user_id;
    private String category;
    private int difficulty;
    private String due_date;
    private float effort;
    private String state;
    private int stimated_time;

    public String getTask_id() {
        return this.task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDue_date() {
        return this.due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public float getEffort() {
        return this.effort;
    }

    public void setEffort(float effort) {
        this.effort = effort;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStimated_time() {
        return this.stimated_time;
    }

    public void setStimated_time(int stimated_time) {
        this.stimated_time = stimated_time;
    }

}
