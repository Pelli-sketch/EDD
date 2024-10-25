/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cobertura_de_sucursales;

/**
 *
 * @author pablo
 */
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.JOptionPane;

public class Cobertura_de_sucursales extends javax.swing.JFrame {

    private Grafo grafo;

    /**
     * Creates new form Cobertura_de_sucursales
     */
    public Cobertura_de_sucursales() {
        grafo = new Grafo("");
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cargarRedDesdeArchivo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("¡ BIENVENIDO A C.D.S inc !");

        jButton1.setText("Ver Grafo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cargarRedDesdeArchivo.setText("Cargar nuevo archivo");
        cargarRedDesdeArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarRedDesdeArchivoActionPerformed(evt);
            }
        });

        jLabel2.setText("¿Qué desea hacer?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cargarRedDesdeArchivo)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jButton1)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cargarRedDesdeArchivo)
                .addGap(58, 58, 58)
                .addComponent(jButton1)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Método que se ejecuta al activar la acción de cargar una red desde un
     * archivo. Este método permite al usuario seleccionar un archivo .json que
     * contiene la información de un sistema de transporte, y luego procesa este
     * archivo para cargar los datos en un grafo.
     *
     * @param evt El evento de acción que desencadena este método.
     */
    private void cargarRedDesdeArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarRedDesdeArchivoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {

            try (FileReader reader = new FileReader(fileChooser.getSelectedFile())) {
                grafo = new Grafo("");
                JsonParser parser = new JsonParser();
                JsonObject sistemaDeTransporteOjeto = parser.parse(reader).getAsJsonObject();
                for (var claveValor : sistemaDeTransporteOjeto.entrySet()) {
                    String nombreSisTransporte = claveValor.getKey();
                    this.grafo.establecerCiudad(nombreSisTransporte);
                    for (var lineaObjeto : claveValor.getValue().getAsJsonArray()) {
                        for (var lineaClaveValor : lineaObjeto.getAsJsonObject().entrySet()) {
                            JsonArray paradas = lineaClaveValor.getValue().getAsJsonArray();
                            for (var paradaElemento : paradas) {
                                if (paradaElemento.isJsonPrimitive()) {
                                    String paradaMetro = paradaElemento.getAsString();
                                    if (this.grafo.getSucursales().getPrimero() == null){
                                        this.grafo.colocarSucursal(paradaMetro);
                                    }else{
                                        var adyacente = this.grafo.getSucursales().getUltimo();
                                        adyacente.adyacentes.agregar(paradaMetro);
                                        this.grafo.colocarSucursal(paradaMetro);
                                        this.grafo.getSucursales().getUltimo().adyacentes.agregar(adyacente.parada);
                                        this.grafo.getGraph().addEdge(adyacente.parada + paradaMetro, adyacente.parada, paradaMetro);
                                    }
                                } else if (paradaElemento.isJsonObject()) {
                                    var Conexion = paradaElemento.getAsJsonObject();
                                    for (var ConexionClaveValor : Conexion.entrySet()) {
                                        String Estacion1 = ConexionClaveValor.getKey();
                                        String Estacion2 = ConexionClaveValor.getValue().getAsString();

                                    }
                                }
                            }
                        }
                    }
                }
                Interfaz2 a = new Interfaz2(this.grafo);
                a.setVisible(true);
                this.setVisible(false);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se selecciono ningun archivo.");
        }


    }//GEN-LAST:event_cargarRedDesdeArchivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.grafo.mostrarGrafo();
//        Interfaz3 b = new Interfaz3();
//        b.setVisible(true);
//        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cobertura_de_sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobertura_de_sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobertura_de_sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobertura_de_sucursales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cobertura_de_sucursales().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargarRedDesdeArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
