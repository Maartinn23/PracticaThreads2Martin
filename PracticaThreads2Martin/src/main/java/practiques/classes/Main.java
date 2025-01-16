/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practiques.classes;

/**
 *
 * @author alumnegs
 */
public class Main 
{
    public static void main(String [] args){
        System.out.println("Iniciant programa...");
        String rutaDirectori = "directoriObservat";
        Publicador publicador = new Publicador();
        Observador observador = new Observador(rutaDirectori, publicador);
        
        
        Thread filObservador = new Thread(observador);
        filObservador.start();
    }
}
