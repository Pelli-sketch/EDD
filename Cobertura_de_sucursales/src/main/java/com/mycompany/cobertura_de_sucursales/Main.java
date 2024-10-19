/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */
public class Main {
    public static void main(String[] args) {
        GestorRedesTransporte gestor = new GestorRedesTransporte();
        gestor.cargarRedDesdeArchivo("ruta/a/tu/archivo.json");
        
        // Puedes acceder a la red cargada
        RedTransporte redActual = gestor.getRedTransporteActual();
        if (redActual != null) {
            System.out.println("Paradas cargadas: " + redActual.getParadas().size());
        }
    }
}
