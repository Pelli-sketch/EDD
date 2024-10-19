package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */
public class Main {
    public static void main(String[] args) {
        GestorRedesTransporte gestor = new GestorRedesTransporte();
        gestor.cargarRedDesdeArchivo("Caracas.json");
        
        // Para acceder a la red cargada
        RedTransporte redActual = gestor.getRedTransporteActual();
        if (redActual != null) {
            System.out.println("Paradas cargadas: " + redActual);
        }
        
        //ejemplo... sinceramente eso es lo que dice la documentación de gson
        gestor.agregarLinea("Línea 6");
        gestor.agregarParadaALinea("Línea 6", "Zoológico");
        gestor.agregarParadaALinea("Línea 6", "La Rinconada");
        
    }
    
   
}
