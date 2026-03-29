package com.mycompany.walmart.Recruitment;

public class Candidate extends Participant {

    public Interview interview;

    public Candidate(Participant p) {
        super();

        this.name = p.name;
        this.id = p.id;
        this.age = p.age;
        this.livesInBogota = p.livesInBogota;
        this.academicLevel = p.academicLevel;
        this.hasExperience = p.hasExperience;
    }

    public boolean isHired() {
        if (this.interview == null) {
            return false;
        }

        if (this.interview.score <= 40) {
            this.interview.alarmSymptoms = "Alto riesgo - No recomendable";
            return false;
        } else if (this.interview.score <= 80) {
            this.interview.alarmSymptoms = "Desempeno medio - Requiere seguimiento";
            return true;
        } else {
            this.interview.alarmSymptoms = "Sin sintomas de alarma";
            return true;
        }
    }
}
