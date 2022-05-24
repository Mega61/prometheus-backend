package com.prometheus.prometheusbackend.entity;

public class Pomodoro {
    
    private String pomodoro_id;
    private int work_time;
    private int short_rest;
    private int long_rest;
    private int pomodori_quantity;

    public String getPomodoro_id() {
        return this.pomodoro_id;
    }

    public void setPomodoro_id(String pomodoro_id) {
        this.pomodoro_id = pomodoro_id;
    }

    public int getWork_time() {
        return this.work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }

    public int getShort_rest() {
        return this.short_rest;
    }

    public void setShort_rest(int short_rest) {
        this.short_rest = short_rest;
    }

    public int getLong_rest() {
        return this.long_rest;
    }

    public void setLong_rest(int long_rest) {
        this.long_rest = long_rest;
    }

    public int getPomodori_quantity() {
        return this.pomodori_quantity;
    }

    public void setPomodori_quantity(int pomodori_quantity) {
        this.pomodori_quantity = pomodori_quantity;
    }
}
