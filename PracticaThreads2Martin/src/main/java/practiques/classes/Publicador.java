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
public class Publicador {

    public void notificar(File fitxer) {
        System.out.println("Arxiu detectat!" + fitxer.getName());

        Thread[] filsCopiadors = new Thread[5];

        for (int i = 0; i < filsCopiadors.length; i++) {
            String rutaSuscriptor = "Suscriptor" + (i + 1);
            filsCopiadors[i] = new Thread(new Copiador(fitxer, rutaSuscriptor));
            filsCopiadors[i].start();
        }

        for (Thread copiador : filsCopiadors) {
            try {
                copiador.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        synchronized(this){
            notify();
        }
        

    }
}
