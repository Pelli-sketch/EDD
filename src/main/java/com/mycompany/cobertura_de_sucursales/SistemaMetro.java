package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */
public class SistemaMetro {
  
    private ListaEnlazada lineas;  // Lista de todas las líneas
    private ListaEnlazada estaciones;  // Lista de todas las estaciones únicas

    public SistemaMetro() {
        this.lineas = new ListaEnlazada();
        this.estaciones = new ListaEnlazada();
    }

    /**
     * Agrega una nueva línea al sistema del metro.
     *
     * @param numeroLinea El número o nombre de la línea (ejemplo: "Linea 6")
     * @param paradas Array de strings con las paradas en orden
     * @return true si se agregó exitosamente, false si hay algún problema
     */
    public boolean agregarLinea(String numeroLinea, String[] paradas) {
        if (paradas == null || paradas.length < 2) {
            return false;  // Una línea debe tener al menos 2 paradas
        }

        // Crear una nueva lista para esta línea
        ListaEnlazada nuevaLinea = new ListaEnlazada();

        // Agregar cada parada a la línea
        for (String parada : paradas) {
            // Manejar el caso de estaciones con conexiones (formato "EstacionA:EstacionB")
            if (parada.contains(":")) {
                String[] conexion = parada.split(":");
                nuevaLinea.agregar(conexion[0].trim());

                // Marcar como estación con conexión
                NodoLista nodo = nuevaLinea.getUltimo();
                nodo.sucursal = true;

                // Agregar la conexión a la lista de adyacentes
                nodo.adyacentes.agregar(conexion[1].trim());
            } else {
                nuevaLinea.agregar(parada.trim());
            }

            // Agregar la parada a la lista general de estaciones si no existe
            if (!estaciones.contiene(parada)) {
                estaciones.agregar(parada);
            }
        }

        // Conectar las paradas adyacentes
        NodoLista actual = nuevaLinea.getPrimero();
        while (actual != null && actual.siguiente != null) {
            // Agregar conexión bidireccional entre paradas adyacentes
            actual.adyacentes.agregar(actual.siguiente.parada);
            actual.siguiente.adyacentes.agregar(actual.parada);
            actual = actual.siguiente;
        }

        // Agregar la línea completa al sistema
        lineas.agregar(numeroLinea);
        NodoLista nodoLinea = lineas.getUltimo();
        nodoLinea.adyacentes = nuevaLinea;

        return true;
    }
}
