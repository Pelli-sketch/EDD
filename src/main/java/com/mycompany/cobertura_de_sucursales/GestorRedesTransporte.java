package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileReader;
import java.io.IOException;

public class GestorRedesTransporte {
    private RedTransporte redTransporteActual;
    private int t; //valor de t
    
    public GestorRedesTransporte() {
        this.t = 3; // puede ser cambiado, esta por defecto
    }
    
    public void seT(int nuevoT) {
        this.t = nuevoT;
        System.out.println("Valor de t actualizado a: " + nuevoT);
    }
    
    public int getT(){
        return t;
    }

    public void cargarRedDesdeArchivo(String rutaArchivo) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            Gson gson = new GsonBuilder().create();

            try (FileReader reader = new FileReader(archivoSeleccionado)) {
                redTransporteActual = gson.fromJson(reader, RedTransporte.class);
                System.out.println("Red de transporte cargada: " + redTransporteActual.nombre);
            } catch (IOException e) {
                System.err.println("Error al cargar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    public void agregarLinea(String nombreLinea) {
        Linea nuevaLinea = new Linea(nombreLinea);
        redTransporteActual.agregarLinea(nuevaLinea);
        System.out.println("Nueva línea agregada: " + nombreLinea);
    }

    public void agregarParadaALinea(String nombreLinea, String nombreParada) {
        Linea linea = buscarLinea(nombreLinea);
        if (linea != null) {
            linea.agregarParada(nombreParada);
            System.out.println("Parada " + nombreParada + " agregada a la línea " + nombreLinea);
        } else {
            System.out.println("La línea " + nombreLinea + " no existe.");
        }
    }
    
    private Linea buscarLinea(String nombreLinea) {
        Linea actual = redTransporteActual.primeraLinea;
        while (actual != null) {
            if (actual.nombre.equals(nombreLinea)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null ;
    }

    public RedTransporte getRedTransporteActual() {
        return redTransporteActual;
    }
    
        // Método para evaluar la cobertura
    public void evaluarCobertura() {
        if (redTransporteActual == null) {
            System.out.println("No hay red de transporte cargada.");
            return;
        }
        System.out.println("Evaluando cobertura con t = " + t);
        
    }
    
    
    
}


