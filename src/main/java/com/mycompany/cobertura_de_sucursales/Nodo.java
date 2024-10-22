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
public class Nodo<T> {
    private T data;
    private Nodo<T> pNext; 

    public Nodo(T data, Nodo<T> pNext) {
        this.data = data;
        this.pNext = pNext;
    }

    public T getData() {
        return data;
    }

    public Nodo<T> getpNext() {
        return pNext;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setpNext(Nodo<T> pNext) {
        this.pNext = pNext;
    }

}
