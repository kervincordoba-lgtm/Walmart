/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.Lectura;

/**
 *
 * @author Kervin Cordoba
 */
import java.util.Scanner;

public class Lectura {

    static Scanner sc1 = new Scanner(System.in);

    public int leerInt(String mensaje) {
        int dato;
        System.out.println(mensaje);
        dato = sc1.nextInt();
        return dato;
    }

    public String leerString(String mensaje) {
        String dato;
        System.out.println(mensaje);
        dato = sc1.next();
        return dato;
    }

    public long leerLong(String mensaje) {
        long dato;
        System.out.println(mensaje);
        dato = sc1.nextLong();
        return dato;
    }
    public double leerDoble(String mensaje){
        double dato;
        System.out.println(mensaje);
        dato= sc1.nextDouble();
        return dato;
    }
    public float leerFloat(String mensaje){
        float dato;
        System.out.println(mensaje);
        dato= sc1.nextFloat();
        return dato;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
}
