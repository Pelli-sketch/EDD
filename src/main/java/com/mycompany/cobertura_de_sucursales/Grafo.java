/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author sebma
 */
public class Grafo  {
    private int T;
    private Lista<Parada> Paradas;
    private String nombreRedTransporte;

    public int getT() {
        return T;
    }

    public void setT(int T) {
        this.T = T;
    }
    
    public String getNombreRedTransporte() {
        return nombreRedTransporte;
    }

    public Lista<Parada> getParadas() {
        return Paradas;
    }

    public void setParadas(Lista<Parada> Paradas) {
        this.Paradas = Paradas;
    }

    public void setNombreRedTransporte(String nombreRedTransporte) {
        this.nombreRedTransporte = nombreRedTransporte;
    }

    public Grafo() {
        Paradas = new Lista<>();
        nombreRedTransporte = "";
        T = 0;

    }


}
