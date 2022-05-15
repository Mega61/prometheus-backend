package com.prometheus.prometheusbackend.entity;

public class User {

    private String client_id;
    private String email;
    private String password;
    private boolean genero;
    private String telefono;
    private String nombre;

    public String getClient_id() {
        return client_id;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

}
