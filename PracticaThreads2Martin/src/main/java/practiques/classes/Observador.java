package practiques.classes;

import java.io.File;

public class Observador implements Runnable {

    private final File directori;
    private final Publicador publicador;

    public Observador(String ruta, Publicador publicador) {
        this.directori = new File(ruta);
        this.publicador = publicador;
    }

    @Override
    public void run() { // Creem el mecanisme infinit de control de fitxers per directoriObservat
        while (true) {
            File[] fitxers = directori.listFiles();
            if (fitxers != null && fitxers.length > 0) {
                for (File fitxer : fitxers) {
                    synchronized (publicador) {
                        publicador.notificar(fitxer);   // notifiquem a publicador del fitxer trobat
                        try {
                            publicador.wait();  // Adormim el fil publicador
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            try {
                Thread.sleep(1000); // pausa per evitar problemes derivats de rendiment pels fils
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
