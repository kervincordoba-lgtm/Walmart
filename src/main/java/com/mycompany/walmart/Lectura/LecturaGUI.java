package com.mycompany.walmart.Lectura;

import javax.swing.JOptionPane;

public class LecturaGUI {

    String dato="";

    public int leerInt(String mensaje) {
        int dato;
        this.dato= JOptionPane.showInputDialog(mensaje);
        dato = Integer.parseInt(this.dato);
        return dato;
    }

    public String leerString(String mensaje) {
        
        dato = JOptionPane.showInputDialog(mensaje);
        return dato;
    }

    public long leerLong(String mensaje) {
        long dato;
        this.dato= JOptionPane.showInputDialog(mensaje);
        dato = Long.parseLong(this.dato);
        return dato;
    }
    public double leerDoble(String mensaje){
        double dato;
        this.dato= JOptionPane.showInputDialog(mensaje);
        dato = Double.parseDouble(this.dato);
        return dato;
    }
    public float leerFloat(String mensaje){
        float dato;
        this.dato= JOptionPane.showInputDialog(mensaje);
        dato = Float.parseFloat(this.dato);
        return dato;
    }

    
    
    public int readMenuOption(String menu, int cancelOption) {
        String optionInput = this.leerString(menu);
        if (optionInput == null) {
            return cancelOption;
        }

        try {
            return Integer.parseInt(optionInput.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
}
