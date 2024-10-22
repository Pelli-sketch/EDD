/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author sebma
 * @param <T>
 */
public class Lista<T> {

    private Nodo<T> pFirst;
    private Nodo<T> pLast;
    private int size;

    public Lista() {
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;
    }

    public Nodo<T> getpFirst() {
        return pFirst;
    }

    public Nodo<T> getpLast() {
        return pLast;
    }

    public int getSize() {
        return size;
    }

    public void InsertarFinal(T valor) {
        if (pFirst == null) {
            pFirst = new Nodo<>(valor, pLast);
            pLast = pFirst;
        } else {
            pLast.setpNext(new Nodo<>(valor, null));
            pLast = pLast.getpNext();

        }
        size++;
    }


}
