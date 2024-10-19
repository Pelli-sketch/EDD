/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */
class Parada {
    String nombre;
    Parada siguiente;

    public Parada(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}

class Linea {
    String nombre;
    Parada primeraParada;
    Linea siguiente;

    public Linea(String nombre) {
        this.nombre = nombre;
        this.primeraParada = null;
        this.siguiente = null;
    }

    public void agregarParada(String nombreParada) {
        Parada nuevaParada = new Parada(nombreParada);
        if (primeraParada == null) {
            primeraParada = nuevaParada;
        } else {
            Parada actual = primeraParada;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaParada;
        }
    }
}

class RedTransporte {
    String nombre;
    Linea primeraLinea;

    public RedTransporte() {
        this.nombre = "";
        this.primeraLinea = null;
    }

    public void agregarLinea(Linea nuevaLinea) {
        if (primeraLinea == null) {
            primeraLinea = nuevaLinea;
        } else {
            Linea actual = primeraLinea;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaLinea;
        }
    }
}