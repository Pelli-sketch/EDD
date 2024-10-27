package com.mycompany.cobertura_de_sucursales;

public class Grafo {

    private int t; // Valor de t según la ciudad
    private ListaEnlazada sucursales; // Lista enlazada
    private String ciudad;// Ciudad seleccionada
    

    /**
     * Constructor de la clase Grafo.
     *
     * @param ciudadInicial La ciudad inicial para establecer el grafo.
     */
    public Grafo(String ciudadInicial) {
        sucursales = new ListaEnlazada(); // Inicializamos la lista enlazada
        establecerCiudad(ciudadInicial);
    }

    /**
     * Obtiene el valor de t.
     *
     * @return El valor de t.
     */
    public int getT() {
        return t;
    }

    /**
     * Establece el valor de t basado en la ciudad seleccionada.
     *
     * @param t El nuevo valor de t.
     */
    public void setT(int t) {
        this.t = t;
    }

    /**
     * Obtiene la lista de sucursales.
     *
     * @return La lista enlazada de sucursales.
     */
    public ListaEnlazada getSucursales() {
        return sucursales; // Devuelve la lista enlazada de sucursales
    }

    // Mostrar grafo
    public void mostrarGrafo() {
//        grafo.display();
    }

    /**
     * Establece la ciudad del grafo y ajusta el valor de t según la ciudad.
     *
     * @param nuevaCiudad La nueva ciudad a establecer.
     */
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

    /**
     * Obtiene un nodo de la lista de sucursales que corresponde a la parada
     * dada.
     *
     * @param parada La parada a buscar.
     * @return El nodo correspondiente a la parada, o null si no se encuentra.
     */
    public NodoLista ObtenerNodo(String parada) {
        NodoLista actual = sucursales.getPrimero();
        while (actual != null) {
            if (actual.parada.equals(parada)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    /**
     * Coloca una nueva sucursal en la parada especificada.
     *
     * @param parada La parada donde se colocará la sucursal.
     * @return true si la sucursal ya existía, false si fue agregada.
     */
    public boolean colocarSucursal(String parada) {
        NodoLista aux = sucursales.getPrimero();
        if (sucursales.contiene(parada)) {
            return true;
        }

        sucursales.agregar(parada); // Agregamos a la lista enlazada
        return false; // La parada no existe
    }

    /**
     * Elimina una sucursal de la parada especificada.
     *
     * @param parada La parada de la que se eliminará la sucursal.
     * @return true si la sucursal fue eliminada, false si no existía.
     */
    public boolean quitarSucursal(String parada) {
        if (sucursales.eliminar(parada)) { // Eliminamos de la lista enlazada
            return true;
        }
        return false; // No hay sucursal en la parada
    }

    /**
     * Realiza una búsqueda en profundidad (DFS) para verificar la cobertura
     * desde una sucursal.
     *
     * @param sucursal La sucursal desde la cual iniciar la búsqueda.
     * @return Una lista de las paradas visitadas durante la búsqueda.
     */
    public ListaEnlazada coberturaDFS(String sucursal) {
        ListaEnlazada visitadas = new ListaEnlazada();
        DFS(sucursal, visitadas, t);
        return visitadas; // Devuelve las paradas visitadas
    }

    private void DFS(String parada, ListaEnlazada visitadas, int profundidad) {
        if (profundidad < 0 || visitadas.contiene(parada)) {
            return;
        }

        visitadas.agregar(parada);
    }

    /**
     * Realiza una búsqueda en amplitud (BFS) para verificar la cobertura desde
     * una sucursal.
     *
     * @param sucursal La sucursal desde la cual iniciar la búsqueda.
     * @return Una lista de las paradas visitadas durante la búsqueda.
     */
    public ListaEnlazada coberturaBFS(String sucursal) {
        ListaEnlazada visitadas = new ListaEnlazada();
        BFS(sucursal, visitadas, t);
        return visitadas; // Devuelve las paradas visitadas
    }

    // Implementación de BFS
    private void BFS(String parada, ListaEnlazada visitadas, int maxProfundidad) {
        ListaDoble lista = new ListaDoble();
        lista.agregarFinal(parada);
        int nivel = 0;

        while (!lista.estaVacia() && nivel <= maxProfundidad) {
            int tamañoNivel = lista.tamaño();
            for (int i = 0; i < tamañoNivel; i++) {
                String paradaActual = lista.removerFrente();
                if (!visitadas.contiene(paradaActual)) {
                    visitadas.agregar(paradaActual);
                }
            }
            nivel++;
        }
    }

    /**
     * Sugiere paradas para colocar sucursales basadas en la lista de paradas
     * sin cobertura.
     *
     * @param paradasSinCobertura La lista de paradas sin cobertura.
     * @return La lista de sugerencias de paradas para colocar sucursales.
     */
    public ListaEnlazada sugerirSucursales(ListaEnlazada paradasSinCobertura) {
        return paradasSinCobertura; // Devuelve las sugerencias
    }

}

/**
 * Clase que representa un nodo doble en una lista doble.
 */
class NodoDoble {

    String parada;
    NodoDoble siguiente;

    /**
     * Constructor de la clase NodoDoble.
     *
     * @param parada La parada asociada a este nodo.
     */
    NodoDoble(String parada) {
        this.parada = parada;
        this.siguiente = null;
    }
}

/**
 * Clase que representa una lista doble.
 */
class ListaDoble {

    private NodoDoble cabeza;
    private NodoDoble cola;

    /**
     * Constructor de la clase ListaDoble.
     */
    public ListaDoble() {
        cabeza = null;
        cola = null;
    }

    /**
     * Agrega un nuevo nodo al final de la lista.
     *
     * @param parada La parada a agregar.
     */
    public void agregarFinal(String parada) {
        NodoDoble nuevo = new NodoDoble(parada);
        if (cabeza == null) {
            // La lista está vacía
            cabeza = nuevo;
            cola = nuevo;
        } else {
            // Agregar al final de la lista
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    /**
     * Remueve el nodo del frente de la lista.
     *
     * @return La parada removida, o null si la lista está vacía.
     */
    public String removerFrente() {
        if (cabeza == null) {
            return null; // o lanzar una excepción si lo prefieres
        }
        String parada = cabeza.parada;
        cabeza = cabeza.siguiente;
        if (cabeza == null) {
            // Si la lista queda vacía, actualizamos cola a null
            cola = null;
        }
        return parada;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Obtiene el tamaño de la lista.
     *
     * @return El tamaño de la lista.
     */
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

/**
 * Clase que representa un nodo en una lista enlazada.
 */
class NodoLista {

    String parada;
    NodoLista siguiente;
    ListaEnlazada adyacentes;
    Boolean sucursal = false;

    /**
     * Constructor de la clase NodoLista.
     *
     * @param parada La parada asociada a este nodo.
     */
    NodoLista(String parada) {
        this.adyacentes = new ListaEnlazada();
        this.parada = parada;
        this.siguiente = null;
    }
}

/**
 * Clase que representa una lista enlazada.
 */
class ListaEnlazada {

    private NodoLista primero;
    private NodoLista ultimo;

    /**
     * Constructor de la clase ListaEnlazada
     */
    public ListaEnlazada() {
        this.primero = null;
    }

    /**
     * Agrega un nuevo nodo a la lista.
     *
     * @param parada La parada a agregar.
     */
    public void agregar(String parada) {
        if (this.primero == null) {
            this.primero = new NodoLista(parada);
            this.ultimo = this.primero;
        } else {
            this.ultimo.siguiente = new NodoLista(parada);
            this.ultimo = this.ultimo.siguiente;
        }

    }

    /**
     * Elimina un nodo de la lista.
     *
     * @param parada La parada a eliminar.
     * @return true si se eliminó, false en caso contrario.
     */
    public boolean eliminar(String parada) {
        if (primero == null) {
            return false;
        }

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

    /**
     * Verifica si la lista contiene una parada específica.
     *
     * @param parada La parada a buscar.
     * @return true si la lista contiene la parada, false en caso contrario.
     */
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

    /**
     * Elimina varios nodos de la lista que se encuentran en otra lista.
     *
     * @param otraLista La lista de nodos a eliminar.
     */
    public void eliminarVarios(ListaEnlazada otraLista) {
        NodoLista actual = otraLista.getPrimero();
        while (actual != null) {
            eliminar(actual.parada);
            actual = actual.siguiente;
        }
    }

    /**
     * Obtiene el primer nodo de la lista.
     *
     * @return El primer nodo de la lista.
     */
    public NodoLista getPrimero() {
        return primero;
    }

    /**
     * Obtiene el último nodo de la lista.
     *
     * @return El último nodo de la lista.
     */
    public NodoLista getUltimo() {
        return ultimo;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return primero == null;
    }

    public String recorrer() {
        NodoLista pAux;
        String resultado = "";
        if (estaVacia()) {
            resultado = "La lista está vacía";
        } else {
            pAux = primero;
            while (pAux != null) {
                resultado += pAux.parada + " "; // Concatenar el nombre de la parada
                pAux = pAux.siguiente; // Próximo nodo
            }
        }
        return resultado.trim(); // Retorna el resultado sin espacios al final
    }

    /**
     * Muestra la lista enlazada.
     */
    public void mostrar() {
        NodoLista actual = primero;
        while (actual != null) {
            System.out.print(actual.parada + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
    

}
    
