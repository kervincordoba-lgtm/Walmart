package com.mycompany.walmart.Recruitment;

import com.mycompany.walmart.Escritura.EscrituraGUI;
import com.mycompany.walmart.Lectura.LecturaGUI;

public class Interview {

    int score;
    String alarmSymptoms;
    LecturaGUI lectura = new LecturaGUI();
    EscrituraGUI escritura = new EscrituraGUI();

    public Interview() {
        this.score = 0;
        this.alarmSymptoms = "";
    }

    int validateYesNo(int value) {
        while (value < 1 || value > 2) {
            value = lectura.leerInt("Seleccion invalida. Intenta de nuevo:\n1. Si\n2. No");
        }
        return value;
    }

    public void addPoints(int answer) {
        if (answer == 1) {
            this.score += 40;
        }
    }

    public void registerInterview() {
        this.score = 0;

        int r1 = lectura.leerInt("Tiene experiencia en atencion al cliente?\n1. Si\n2. No");
        r1 = validateYesNo(r1);
        addPoints(r1);

        int r2 = lectura.leerInt("Puede trabajar fines de semana?\n1. Si\n2. No");
        r2 = validateYesNo(r2);
        addPoints(r2);

        int r3 = lectura.leerInt("Sabe trabajar en equipo?\n1. Si\n2. No");
        r3 = validateYesNo(r3);
        addPoints(r3);

        escritura.mostrarMensaje("Entrevista realizada con exito.");
    }
}
