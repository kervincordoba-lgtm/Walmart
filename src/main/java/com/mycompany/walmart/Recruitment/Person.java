package com.mycompany.walmart.Recruitment;

import com.mycompany.walmart.Escritura.EscrituraGUI;
import com.mycompany.walmart.Lectura.LecturaGUI;

public abstract class Person {

    String name;
    String id;
    int age;
    protected static final LecturaGUI lectura = new LecturaGUI();
    protected static final EscrituraGUI escritura = new EscrituraGUI();

    public Person() {
        this.name = "";
        this.id = "";
        this.age = 0;
    }

    public void registerPerson() {
        String inputName = lectura.leerString("Nombre:");
        this.name = inputName == null ? "" : inputName;

        int inputAge = lectura.leerInt("Edad:");
        this.age = inputAge == Integer.MIN_VALUE ? 0 : inputAge;

        String inputId = lectura.leerString("Numero de documento:");
        this.id = inputId == null ? "" : inputId;
    }
}
