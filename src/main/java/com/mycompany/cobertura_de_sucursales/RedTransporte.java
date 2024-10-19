package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */

/**
 * Representa una parada en una línea de transporte.
 */
class Parada {
    String nombre;
    Parada siguiente;
    /**
     * Constructor de la parada.
     * @param nombre Nombre de la parada.
     */
    public Parada(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}
/**
 * Representa una línea de transporte.
 */
class Linea {
    String nombre;
    Parada primeraParada;
    Linea siguiente;
    /**
     * Constructor de la línea.
     * @param nombre Nombre de la línea.
     */
    public Linea(String nombre) {
        this.nombre = nombre;
        this.primeraParada = null;
        this.siguiente = null;
    }
    /**
     * Agrega una nueva parada a la línea.
     * @param nombreParada Nombre de la nueva parada.
     */
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
/**
 * Representa una red de transporte completa.
 */
class RedTransporte {
    String nombre;
    Linea primeraLinea;
    /**
     * Constructor de la red de transporte.
     */
    public RedTransporte() {
        this.nombre = "";
        this.primeraLinea = null;
    }
    /**
     * Agrega una nueva línea a la red de transporte.
     * @param nuevaLinea La nueva línea a agregar.
     */
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