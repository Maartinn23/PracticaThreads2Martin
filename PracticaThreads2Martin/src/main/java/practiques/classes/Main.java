/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practiques.classes;

/**
 *
 * @author alumnegs
 */
public class Main // clase main on instanciem les clases i els fils.
{   
    public static void main(String [] args){    
        System.out.println("Iniciant programa...");
        

        String rutaDirectori = "directoriObservat";
        Publicador publicador = new Publicador();
        Observador observador = new Observador(rutaDirectori, publicador);
        
        
        Thread filPublicador = new Thread(publicador);
        Thread filObservador = new Thread(observador);
        
        filPublicador.start();
        filObservador.start();
    }
}
