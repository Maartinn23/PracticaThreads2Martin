/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practiques.classes;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumnegs
 */
public class Observador implements Runnable {

    private File directori;
    private Publicador publicador;

    public Observador(String ruta, Publicador publicador) {
        this.directori = new File(ruta);
        this.publicador = publicador;
    }

    @Override
    public void run() throws UnsupportedOperationException {
        while (true) {
            File[] fitxers = directori.listFiles();
            if (fitxers != null && fitxers.length > 0) {
                synchronized (publicador) {
                    publicador.notificar(fitxers[0]);
                    try {
                        publicador.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                    }
                }
            }
        }
    }

}
