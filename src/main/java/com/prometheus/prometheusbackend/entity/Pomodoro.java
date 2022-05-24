package com.prometheus.prometheusbackend.entity;

public class Pomodoro {
    
    private String pomodoro_id;
    private int work_time;
    private int short_rest;
    private int pomodori_quantity;
    private int pauses_quantity;
    private int actual_work_time;


    public Pomodoro(String pomodoro_id, int work_time, int short_rest, int pomodori_quantity, int pauses_quantity) {
        this.pomodoro_id = pomodoro_id;
        this.work_time = work_time;
        this.short_rest = short_rest;
        this.pomodori_quantity = pomodori_quantity;
        this.pauses_quantity = pauses_quantity;
        setActual_work_time();
    }


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

    public int getPomodori_quantity() {
        return this.pomodori_quantity;
    }

    public void setPomodori_quantity(int pomodori_quantity) {
        this.pomodori_quantity = pomodori_quantity;
    }


    public int getPauses_quantity() {
        return this.pauses_quantity;
    }

    public void setPauses_quantity(int pauses_quantity) {
        this.pauses_quantity = pauses_quantity;
    }

    public int getActual_work_time() {
        return this.actual_work_time;
    }

    public void setActual_work_time() {
        this.actual_work_time = this.work_time * this.pomodori_quantity;
    }

}
