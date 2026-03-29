package com.mycompany.walmart.Recruitment;

import com.mycompany.walmart.Escritura.EscrituraGUI;
import com.mycompany.walmart.Lectura.LecturaGUI;
import java.util.ArrayList;

public class RecruitmentResult {

    ArrayList<Participant> applicants;
    ArrayList<Candidate> eligible;
    ArrayList<Participant> rejected;
    ArrayList<Candidate> hired;
    LecturaGUI lectura = new LecturaGUI();
    EscrituraGUI escritura = new EscrituraGUI();

    public RecruitmentResult() {
        this.applicants = new ArrayList<>();
        this.eligible = new ArrayList<>();
        this.rejected = new ArrayList<>();
        this.hired = new ArrayList<>();
    }

    public void addApplicant(Participant p) {
        if (p != null) {
            this.applicants.add(p);
        }
    }

    public void filterCandidates(Participant p) {
        if (p == null) {
            return;
        }

        boolean isAdult = p.age >= 18;
        boolean isHighSchoolGrad = "Bachiller".equals(p.academicLevel);

        if (isAdult && p.livesInBogota && isHighSchoolGrad && p.hasExperience) {
            Candidate candidate = new Candidate(p);
            this.eligible.add(candidate);
        } else {
            this.rejected.add(p);
        }

        escritura.mostrarMensaje("Clasificacion realizada.");
    }

    public void showEligible() {
        if (this.eligible.isEmpty()) {
            escritura.mostrarMensaje("Aun no hay candidatos aptos en la lista.");
            return;
        }

        String aptos = "CANDIDATOS APTOS:\n\n";
        for (int i = 0; i < this.eligible.size(); i++) {
            aptos += this.eligible.get(i).name + " CC:" + this.eligible.get(i).id + "\n";
        }

        escritura.mostrarMensaje(aptos);
    }

    public void showRejected() {
        if (this.rejected.isEmpty()) {
            escritura.mostrarMensaje("Aun no hay candidatos no aptos en la lista.");
            return;
        }

        String noAptos = "CANDIDATOS NO APTOS:\n\n";
        for (int i = 0; i < this.rejected.size(); i++) {
            noAptos += this.rejected.get(i).name + " ID:" + this.rejected.get(i).id + "\n";
        }

        escritura.mostrarMensaje(noAptos);
    }

    public void showHired() {
        if (this.hired.isEmpty()) {
            escritura.mostrarMensaje("Aun no hay candidatos contratados en la lista.");
            return;
        }

        String contratados = "CANDIDATOS CONTRATADOS:\n\n";
        for (int i = 0; i < this.hired.size(); i++) {
            Candidate c = this.hired.get(i);
            contratados += c.name
                    + " CC:" + c.id
                    + " | Puntaje: " + c.interview.score
                    + " | Diagnostico: " + c.interview.alarmSymptoms
                    + "\n";
        }

        escritura.mostrarMensaje(contratados);
    }

    public void addHired(Candidate c) {
        if (c == null || c.interview == null) {
            escritura.mostrarMensaje("No se puede contratar sin entrevista.");
            return;
        }

        if (!c.isHired()) {
            escritura.mostrarMensaje("Candidato no recomendado para contratacion.");
            return;
        }

        for (int i = 0; i < this.hired.size(); i++) {
            if (this.hired.get(i).id.equals(c.id)) {
                escritura.mostrarMensaje("El candidato ya fue registrado como contratado.");
                return;
            }
        }

        this.hired.add(c);
        this.eligible.remove(c);
        escritura.mostrarMensaje("Candidato contratado.");
    }

    public Candidate findCandidate() {
        if (this.eligible.isEmpty()) {
            escritura.mostrarMensaje("No hay candidatos aptos para entrevistar.");
            return null;
        }

        String idToFind = lectura.leerString("Ingrese el numero de documento del candidato a entrevistar:");
        if (idToFind == null || idToFind.isBlank()) {
            escritura.mostrarMensaje("Documento invalido.");
            return null;
        }

        for (int i = 0; i < this.eligible.size(); i++) {
            Candidate c = this.eligible.get(i);
            if (c.id.equals(idToFind)) {
                return c;
            }
        }

        escritura.mostrarMensaje("El candidato que buscas no existe.");
        return null;
    }
}
