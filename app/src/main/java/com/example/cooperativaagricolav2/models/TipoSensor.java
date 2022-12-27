package com.example.cooperativaagricolav2.models;

public class TipoSensor {

    private int id;
    private String DateTime;
    private float Lectura;

    public TipoSensor(int id, String dateTime, float lectura) {
        this.id = id;
        DateTime = dateTime;
        Lectura = lectura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public float getLectura() {
        return Lectura;
    }

    public void setLectura(float lectura) {
        Lectura = lectura;
    }
}
