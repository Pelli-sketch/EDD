/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */
import java.util.List;

public class RedTransporte {
    private String nombre;
    private List<Parada> paradas;

    // Constructor, getters y setters
    public RedTransporte(String nombre, List<Parada> paradas) {
        this.nombre = nombre;
        this.paradas = paradas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Parada> getParadas() {
        return paradas;
    }
}

class Parada {
    private String id;
    private double latitud;
    private double longitud;

    // Constructor, getters y setters
    public Parada(String id, double latitud, double longitud) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId() {
        return id;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
