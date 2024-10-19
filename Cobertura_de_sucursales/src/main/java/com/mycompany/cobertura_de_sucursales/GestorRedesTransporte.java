/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;

public class GestorRedesTransporte {
    private RedTransporte redTransporteActual;

    public void cargarRedDesdeArchivo(String rutaArchivo) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            RedTransporte nuevaRed = gson.fromJson(reader, RedTransporte.class);
            this.redTransporteActual = nuevaRed;
            System.out.println("Red de transporte cargada: " + nuevaRed.getNombre());
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public RedTransporte getRedTransporteActual() {
        return redTransporteActual;
    }
}
