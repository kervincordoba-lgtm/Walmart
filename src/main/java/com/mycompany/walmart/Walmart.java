package com.mycompany.walmart;

import com.mycompany.walmart.Escritura.EscrituraGUI;
import com.mycompany.walmart.Lectura.LecturaGUI;
import com.mycompany.walmart.Payroll.*;
import com.mycompany.walmart.Recruitment.*;
import java.util.ArrayList;

public class Walmart {

    LecturaGUI lectura = new LecturaGUI();
    EscrituraGUI escritura = new EscrituraGUI();
    ArrayList<Contract> payrollContracts = new ArrayList<>();

    private void showRegisteredContracts() {
        if (this.payrollContracts.isEmpty()) {
            escritura.mostrarMensaje("Aun no hay contratos registrados en nomina.");
            return;
        }

        String data = "CONTRATOS REGISTRADOS\n\n";
        for (int i = 0; i < this.payrollContracts.size(); i++) {
            Contract c = this.payrollContracts.get(i);
            data += (i + 1) + ". " + c.getJob() + " | " + c.getContractType() + "\n";
        }

        escritura.mostrarMensaje(data);
    }

    public void showRecuitmentMenu() {
        RecruitmentResult recruitment = new RecruitmentResult();
        int option;

        do {
            String recruitmentMenu
                    = "MENU RECLUTAMIENTO WALMART\n"
                    + "1. Registrar hoja de vida\n"
                    + "2. Ver aptos\n"
                    + "3. Ver no aptos\n"
                    + "4. Realizar entrevistas\n"
                    + "5. Ver contratados\n"
                    + "6. Salir\n";

            option = lectura.readMenuOption(recruitmentMenu, 6);

            switch (option) {
                case 1:
                    Participant p = new Participant();
                    p.registerPerson();
                    p.registerCV();

                    recruitment.addApplicant(p);
                    recruitment.filterCandidates(p);
                    break;

                case 2:
                    recruitment.showEligible();
                    break;

                case 3:
                    recruitment.showRejected();
                    break;

                case 4:
                    Candidate selectedCandidate = recruitment.findCandidate();
                    if (selectedCandidate == null) {
                        break;
                    }

                    Interview interview = new Interview();
                    selectedCandidate.interview = interview;
                    selectedCandidate.interview.registerInterview();
                    recruitment.addHired(selectedCandidate);
                    break;

                case 5:
                    recruitment.showHired();
                    break;

                case 6:
                    escritura.mostrarMensaje("Proceso finalizado.");
                    break;

                default:
                    escritura.mostrarMensaje("Has seleccionado una opcion invalida. Intenta de nuevo.");
                    break;
            }
        } while (option != 6);
    }

    public void showPayroll() {
        int option;

        do {
            String payrollMenu = "MENU NOMINA Y SALARIOS\n"
                    + "1. Registrar cargo y tipo de contrato\n"
                    + "2. Ver contratos registrados\n"
                    + "3. Calcular salario por cargo\n"
                    + "4. Ver detalle de salario por cargo\n"
                    + "5. Salir\n";

            option = lectura.readMenuOption(payrollMenu, 5);

            switch (option) {
                case 1:
                    String contractType = Contract.requestContractType(lectura, escritura);
                    if (contractType == null) {
                        break;
                    }

                    Contract newContract = Contract.createContractByTypeName(contractType);
                    if (newContract == null) {
                        escritura.mostrarMensaje("No se pudo crear el contrato para el tipo seleccionado.");
                        break;
                    }

                    if (newContract.createContract()) {
                        payrollContracts.add(newContract);
                        escritura.mostrarMensaje("Contrato registrado correctamente.");
                    }
                    break;

                case 2:
                    showRegisteredContracts();
                    break;

                case 3:
                    Contract selectedForCalculation = Contract.selectContractFromList(
                            this.payrollContracts,
                            "Selecciona el cargo para calcular salario:",
                            lectura,
                            escritura
                    );
                    if (selectedForCalculation == null) {
                        break;
                    }

                    escritura.mostrarMensaje(
                            "Cargo seleccionado: " + selectedForCalculation.getJob()
                            + "\nTipo de contrato: " + selectedForCalculation.getContractType()
                    );
                    selectedForCalculation.recalculatePayroll();
                    escritura.mostrarMensaje(selectedForCalculation.showPayrollDetail());
                    break;

                case 4:
                    Contract selectedForDetail = Contract.selectContractFromList(
                            this.payrollContracts,
                            "Selecciona el cargo para ver detalle:",
                            lectura,
                            escritura
                    );
                    if (selectedForDetail == null) {
                        break;
                    }

                    if (!selectedForDetail.hasPayrollCalculated()) {
                        escritura.mostrarMensaje("Este contrato aun no tiene liquidacion calculada.");
                        break;
                    }

                    escritura.mostrarMensaje(selectedForDetail.showPayrollDetail());
                    break;

                case 5:
                    escritura.mostrarMensaje("Saliendo del modulo de nomina.");
                    break;

                default:
                    escritura.mostrarMensaje("Has seleccionado una opcion invalida. Intenta de nuevo.");
                    break;
            }
        } while (option != 5);
    }

    public static void main(String[] args) {
        int option;
        Walmart system = new Walmart();

        do {
            String menu = "WALMART - SISTEMA RH\n"
                    + "1. Menu de Reclutamiento\n"
                    + "2. Menu de Nomina y Salarios\n"
                    + "3. Salir";

            option = system.lectura.readMenuOption(menu, 3);

            switch (option) {
                case 1:
                    system.showRecuitmentMenu();
                    break;
                case 2:
                    system.showPayroll();
                    break;
                case 3:
                    system.escritura.mostrarMensaje("Proceso finalizado.");
                    break;
                default:
                    system.escritura.mostrarMensaje("Has seleccionado una opcion invalida. Intenta de nuevo.");
                    break;
            }
        } while (option != 3);
    }
}
