package practiques.classes;

import java.io.File;

public class Publicador implements Runnable {

    private File fitxer;
    private final Object lock = new Object();

    public void notificar(File fitxer) {    // Métode per notificar la existencia d'un fitxer i per "bloquejar" la
        synchronized (lock) {               // sincronització
            this.fitxer = fitxer;
            lock.notify();
        }
    }

    @Override
    public void run() { // Aqui també generem el mecanisme infinit de control de fitxers
        while (true) {
            synchronized (lock) {   // Sincronizitem si no esta bloqueig actiu
                while (fitxer == null) {
                    try {
                        lock.wait();    // Posem el bloqueig en espera
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                System.out.println("Arxiu detectat! " + fitxer.getName());
                Thread[] filsCopiadors = new Thread[5]; // Instanciem els fils (un per cada publicador amb la seva copia)

                for (int i = 0; i < filsCopiadors.length; i++) {    // Asignem cada copia a cada publicador
                    String rutaSuscriptor = "Suscriptor" + (i + 1);
                    filsCopiadors[i] = new Thread(new Copiador(fitxer, rutaSuscriptor));
                    filsCopiadors[i].start(); // Engeguem els fils
                }

                for (Thread copiador : filsCopiadors) {
                    try {
                        copiador.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (fitxer.delete()) {
                    System.out.println("Arxiu original eliminat: " + fitxer.getName()); // Borrem el fitxer original de directoriObservat
                } else {
                    System.out.println("No s'ha pogut eliminar l'arxiu original: " + fitxer.getName());
                }

                fitxer = null;
            }
        }
    }
}
