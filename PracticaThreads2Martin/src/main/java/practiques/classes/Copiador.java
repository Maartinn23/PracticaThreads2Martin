/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practiques.classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author alumnegs
 */
public class Copiador implements Runnable {

    private File fitxer;
    private String rutaDesti;

    public Copiador(File fitxer, String rutaDesti) {
        this.fitxer = fitxer;
        this.rutaDesti = rutaDesti;
    }

    @Override
    public void run() throws UnsupportedOperationException {    
        try {
            File directoriDesti = new File(rutaDesti);  // Declarem la ubicació desti de les copies
            if (!directoriDesti.exists()) {
                directoriDesti.mkdir();
            }

            File fitxerDesti = new File(directoriDesti, fitxer.getName());
            Files.copy(fitxer.toPath(), fitxerDesti.toPath()); // Copiem els fitxers a la ubicació desí
            System.out.println("Fitxer copiat a " + rutaDesti);
        } catch (IOException e) {
            e.getMessage();
        }

    }

}
