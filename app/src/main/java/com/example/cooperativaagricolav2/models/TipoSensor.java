package com.example.cooperativaagricolav2.models;

public class TipoSensor {

    private int id;
    private String tipoSensor;

    public TipoSensor(int id, String tipoSensor) {
        this.id = id;
        this.tipoSensor = tipoSensor;
    }

    public TipoSensor(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }
}
