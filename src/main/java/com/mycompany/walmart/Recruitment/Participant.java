package com.mycompany.walmart.Recruitment;

public class Participant extends Person {

    boolean livesInBogota;
    String academicLevel;
    boolean hasExperience;

    public Participant() {
        super();

        this.academicLevel = "";
        this.livesInBogota = false;
        this.hasExperience = false;
    }

    public int validateInput(int i) {
        while (i < 1 || i > 2) {
            i = lectura.leerInt(
                    "Seleccionaste una opcion invalida. Intenta de nuevo:\n1. Si\n2. No"
            );
        }
        return i;
    }

    public void registerCV() {
        int vive = lectura.leerInt("Vive en Bogota?\n1. Si\n2. No");
        vive = validateInput(vive);

        if (vive == 1) {
            this.livesInBogota = true;
        } else {
            int hours = lectura.leerInt("A cuantas horas esta de Bogota?");
            this.livesInBogota = hours <= 2;
        }

        int bach = lectura.leerInt("Tiene titulo de bachiller?\n1. Si\n2. No");
        bach = validateInput(bach);
        this.academicLevel = bach == 1 ? "Bachiller" : "No Bachiller";

        int exp = lectura.leerInt("Tiene experiencia laboral?\n1. Si\n2. No");
        exp = validateInput(exp);
        this.hasExperience = exp == 1;

        escritura.mostrarMensaje("Hoja de vida registrada.");
    }
}
