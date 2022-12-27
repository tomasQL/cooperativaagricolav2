package com.example.cooperativaagricolav2.models;

public class Sensor {

    private int id;
    private String nombre;
    private String descripcion;
    private float ideal;

    public Sensor(int id, String nombre, String descripcion, float ideal) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ideal = ideal;
    }

    public Sensor(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getIdeal() {
        return ideal;
    }

    public void setIdeal(float ideal) {
        this.ideal = ideal;
    }
}
