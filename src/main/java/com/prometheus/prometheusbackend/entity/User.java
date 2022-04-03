package com.prometheus.prometheusbackend.entity;

public class User {

    private int client_id;
    private String email;
    private String password;

    public int getClient_id() {
        return client_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

}
