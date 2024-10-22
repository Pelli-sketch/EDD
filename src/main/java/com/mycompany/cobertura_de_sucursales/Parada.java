/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author sebma
 */
public class Parada {
    private String nombre;
    private String linea;
    private Lista<Parada> siguientes;

    public Parada(String nombre, String linea) {
        this.nombre = nombre;
        this.linea = linea;
        siguientes = new Lista<>();    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Lista<Parada> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(Lista<Parada> siguientes) {
        this.siguientes = siguientes;
    }
    /**
     * Constructor de la parada.
     * @param nombre Nombre de la parada.
     */
    
}


