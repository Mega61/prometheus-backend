package com.prometheus.prometheusbackend.model;

public class LoginResponse {
    
    private boolean validity;
    private String id;
    private String name;


    public LoginResponse(boolean validity, String id, String name) {
        this.validity = validity;
        this.id = id;
        this.name = name;
    }


    public boolean isValidity() {
        return this.validity;
    }

    public boolean getValidity() {
        return this.validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
