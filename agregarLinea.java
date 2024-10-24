package com.mycompany.cobertura_de_sucursales;


public class agregarLinea {

    private Grafo grafo; // Instancia del grafo de transporte
    private ListaEnlazada nuevaLinea; // Lista enlazada para la secuencia de paradas de la nueva línea

    public agregarLinea(Grafo grafo) {
        this.grafo = grafo;
        this.nuevaLinea = new ListaEnlazada(); // Inicializa la nueva línea con nuestra propia lista enlazada
    }

    // Método para agregar una parada a la nueva línea
    public void agregarParadaALinea(String parada) {
        if (!nuevaLinea.contiene(parada)) {
            nuevaLinea.agregar(parada); // Agregar a la lista enlazada si no existe
            System.out.println("Parada agregada: " + parada);
        }
    }

    // Método para verificar si hay sucursales en las paradas y sugerir nuevas
    public void verificarSucursalesYSugerir() {
        ListaEnlazada sucursalesFaltantes = new ListaEnlazada();
        
        // Iterar sobre las paradas de la nueva línea
        NodoLista actual = nuevaLinea.getPrimero(); // Iterar usando nuestra propia lista enlazada
        while (actual != null) {
            String parada = actual.parada;
            
            // Si no hay sucursal en la parada, se sugiere colocar una
            if (!grafo.getSucursales().contiene(parada)) {
                System.out.println("No hay sucursal en la parada: " + parada);
                sucursalesFaltantes.agregar(parada);
            }
            
            actual = actual.siguiente;
        }
        
        // Mostrar sugerencias
        if (!sucursalesFaltantes.estaVacia()) {
            System.out.println("Sucursales sugeridas en las paradas: ");
            sucursalesFaltantes.mostrar();
        } else {
            System.out.println("Todas las paradas ya tienen sucursal.");
        }
    }

    // Método para confirmar y agregar la nueva línea al grafo
    public void confirmarNuevaLinea() {
        NodoLista actual = nuevaLinea.getPrimero();
        while (actual != null) {
            String parada = actual.parada;
            if (grafo.getGraph().getNode(parada) == null) {
                // Si la parada no existe en el grafo, se crea y se añade
                grafo.getGraph().addNode(parada).setAttribute("ui.label", parada);
            }
            actual = actual.siguiente;
        }
        System.out.println("Línea agregada al grafo.");
    }
}
