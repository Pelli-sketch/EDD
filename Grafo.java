package com.mycompany.cobertura_de_sucursales;


import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Grafo {
    private Graph graph;
    private int t; // Valor de t según la ciudad
    private ListaEnlazada sucursales; // Lista enlazada
    private String ciudad; // Ciudad seleccionada

    public Grafo(String ciudadInicial) {
        graph = new SingleGraph("Red de Transporte");
        sucursales = new ListaEnlazada(); // Inicializamos la lista enlazada
        graph.setAttribute("ui.stylesheet", "node { size: 20px; fill-color: red; } edge { fill-color: gray; }");
        establecerCiudad(ciudadInicial);
    }

    // Mostrar grafo
    public void mostrarGrafo() {
        graph.display();
    }

    // Establecer valor de t basado en la ciudad
    public void establecerCiudad(String nuevaCiudad) {
        ciudad = nuevaCiudad;
        if (ciudad.equalsIgnoreCase("Caracas")) {
            t = 3;
        } else if (ciudad.equalsIgnoreCase("Bogotá")) {
            t = 10;
        } else {
            t = 2; // Valor por defecto
        }
    }

    // Colocar sucursal en una parada
    public boolean colocarSucursal(String parada) {
        if (graph.getNode(parada) != null) {
            sucursales.agregar(parada); // Agregamos a la lista enlazada
            graph.getNode(parada).setAttribute("ui.class", "sucursal");
            graph.getNode(parada).setAttribute("ui.label", "Sucursal");
            return true;
        }
        return false; // La parada no existe
    }

    // Des-seleccionar sucursal
    public boolean quitarSucursal(String parada) {
        if (sucursales.eliminar(parada)) { // Eliminamos de la lista enlazada
            graph.getNode(parada).removeAttribute("ui.class");
            graph.getNode(parada).setAttribute("ui.label", "");
            return true;
        }
        return false; // No hay sucursal en la parada
    }

    // Ver cobertura con DFS
    public ListaEnlazada coberturaDFS(String sucursal) {
        ListaEnlazada visitadas = new ListaEnlazada();
        dfs(sucursal, visitadas, t);
        return visitadas; // Devuelve las paradas visitadas
    }

 private void dfs(String parada, ListaEnlazada visitadas, int profundidad) {
    if (profundidad < 0 || visitadas.contiene(parada)) {
        return;
    }
    
    visitadas.agregar(parada);
    Node nodo = graph.getNode(parada);
    
    // Usar edges() para obtener todas las aristas del nodo
    nodo.edges().forEach(arista -> {
        // Obtener el nodo opuesto directamente
        Node vecinoNodo = arista.getTargetNode(); // Obtener el nodo objetivo de la arista
        String vecino = vecinoNodo.getId(); // Obtener el ID del nodo opuesto
        dfs(vecino, visitadas, profundidad - 1);
    });
}

    // Ver cobertura con BFS
    public ListaEnlazada coberturaBFS(String sucursal) {
        ListaEnlazada visitadas = new ListaEnlazada();
        bfs(sucursal, visitadas, t);
        return visitadas; // Devuelve las paradas visitadas
    }

    // Implementación de BFS
   private void bfs(String parada, ListaEnlazada visitadas, int maxProfundidad) {
    ListaDoble lista = new ListaDoble();
    lista.agregarFinal(parada);
    int nivel = 0;

    while (!lista.estaVacia() && nivel <= maxProfundidad) {
        int tamañoNivel = lista.tamaño();
        for (int i = 0; i < tamañoNivel; i++) {
            String paradaActual = lista.removerFrente();
            if (!visitadas.contiene(paradaActual)) {
                visitadas.agregar(paradaActual);
                Node nodo = graph.getNode(paradaActual);
                
                // Usar edges() para obtener todas las aristas del nodo
                nodo.edges().forEach(arista -> {
                    // Obtener el nodo opuesto directamente
                    Node vecinoNodo = arista.getTargetNode(); // Obtener el nodo objetivo de la arista
                    String vecino = vecinoNodo.getId(); // Obtener el ID del nodo opuesto
                    lista.agregarFinal(vecino);
                });
            }
        }
        nivel++;
    }
}

    // Revisar cobertura total de la ciudad
    public ListaEnlazada revisarCoberturaTotal() {
        ListaEnlazada paradasSinCobertura = new ListaEnlazada();

        for (Node nodo : graph) {
            paradasSinCobertura.agregar(nodo.getId());
        }

        NodoLista actual = sucursales.getPrimero();
        while (actual != null) {
            ListaEnlazada cubiertas = new ListaEnlazada();
            dfs(actual.parada, cubiertas, t);
            paradasSinCobertura.eliminarVarios(cubiertas);
            actual = actual.siguiente;
        }

        return paradasSinCobertura; // Devuelve las paradas sin cobertura
    }

    // Sugerir paradas para colocar sucursales
    public ListaEnlazada sugerirSucursales(ListaEnlazada paradasSinCobertura) {
        return paradasSinCobertura; // Devuelve las sugerencias
    }
}
class NodoDoble {
    String parada;
    NodoDoble siguiente;
    NodoDoble anterior;

    NodoDoble(String parada) {
        this.parada = parada;
        this.siguiente = null;
        this.anterior = null;
    }
}

class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;

    public ListaDoble() {
        cabeza = null;
        cola = null;
    }

    public void agregarFinal(String parada) {
        NodoDoble nuevo = new NodoDoble(parada);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    public String removerFrente() {
        if (cabeza == null) {
            return null; // o lanza una excepción si prefieres
        }
        String parada = cabeza.parada;
        cabeza = cabeza.siguiente;
        if (cabeza != null) {
            cabeza.anterior = null;
        } else {
            cola = null; // Si la lista queda vacía, también actualiza la cola
        }
        return parada;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamaño() {
        int tamaño = 0;
        NodoDoble actual = cabeza;
        while (actual != null) {
            tamaño++;
            actual = actual.siguiente;
        }
        return tamaño;
    }
}
// Implementación de la clase ListaEnlazada
class NodoLista {
    String parada;
    NodoLista siguiente;

    NodoLista(String parada) {
        this.parada = parada;
        this.siguiente = null;
    }
}

class ListaEnlazada {
    private NodoLista primero;

    public ListaEnlazada() {
        this.primero = null;
    }

    public void agregar(String parada) {
        NodoLista nuevo = new NodoLista(parada);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoLista temp = primero;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public boolean eliminar(String parada) {
        if (primero == null) return false;

        if (primero.parada.equals(parada)) {
            primero = primero.siguiente;
            return true;
        }

        NodoLista actual = primero;
        while (actual.siguiente != null) {
            if (actual.siguiente.parada.equals(parada)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean contiene(String parada) {
        NodoLista actual = primero;
        while (actual != null) {
            if (actual.parada.equals(parada)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void eliminarVarios(ListaEnlazada otraLista) {
        NodoLista actual = otraLista.getPrimero();
        while (actual != null) {
            eliminar(actual.parada);
            actual = actual.siguiente;
        }
    }

    public NodoLista getPrimero() {
        return primero;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void mostrar() {
        NodoLista actual = primero;
        while (actual != null) {
            System.out.print(actual.parada + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
