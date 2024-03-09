package com.example.presupuestoapp;

public class Transacciones {

    public Double importe;
    public String descripcion;
    public Integer mes;

    public Transacciones(Double imp, String desc, Integer mes){
        this.importe=imp;
        this.descripcion=desc;
        this.mes=mes;
    }
}
