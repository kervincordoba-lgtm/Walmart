package com.mycompany.walmart.Payroll;

import com.mycompany.walmart.Escritura.EscrituraGUI;
import com.mycompany.walmart.Lectura.LecturaGUI;
import java.util.ArrayList;

public abstract class Contract {

    protected final LecturaGUI lectura = new LecturaGUI();
    protected final EscrituraGUI escritura = new EscrituraGUI();

    // Constantes
    protected static final double AUXILIO_TRANSPORTE = 249095;
    protected static final double SMMLV = 1750905;
    protected static final double UVT = 52374;

    // Atributos
    protected String job;
    protected String contractType;
    protected int hourlyPay;
    protected int hoursWorked;
    protected int dayOvertimeHours;
    protected int nightOvertimeHours;

    protected double grossSalary;
    protected double totalDeductions;
    protected double totalBonus;
    protected double netSalary;
    protected double baseSalary;

    // Deducciones
    protected double healthInsurance;
    protected double pensionContribution;
    protected double solidarityFundContribution;
    protected double withholdingTax;

    // Bonificaciones
    protected double serviceBonus;
    protected double severancePay;

    public Contract() {
        this.job = "";
        this.contractType = "";
        this.hourlyPay = 0;
        this.hoursWorked = 0;
        this.dayOvertimeHours = 0;
        this.nightOvertimeHours = 0;
        this.grossSalary = 0;
        this.totalDeductions = 0;
        this.totalBonus = 0;
        this.netSalary = 0;
        this.baseSalary = 0;
        this.healthInsurance = 0;
        this.pensionContribution = 0;
        this.solidarityFundContribution = 0;
        this.withholdingTax = 0;
        this.serviceBonus = 0;
        this.severancePay = 0;
    }

    public String getJob() {
        return this.job;
    }

    public String getContractType() {
        return this.contractType;
    }

    public static Contract createContractByTypeName(String contractType) {
        if (contractType == null) {
            return null;
        }

        switch (contractType) {
            case "Prestacion de Servicios":
                return new IndependentContractorAgreement();
            case "Salario Integral":
                return new ComprehensiveContract();
            case "Trabajo por Horas":
                return new HourlyJob();
            case "Salario Mensual":
                return new MonthlySalary();
            case "Extralegales Pagados por la Empresa":
                return new AdditionalBenefits();
            default:
                return null;
        }
    }

    public boolean hasPayrollCalculated() {
        return this.grossSalary != 0 || this.totalDeductions != 0 || this.totalBonus != 0 || this.netSalary != 0;
    }

    public static String requestContractType(LecturaGUI lectura, EscrituraGUI escritura) {
        do{
            String menu = "WALMART - SISTEMA RH\n"
                    + "1. Prestacion de Servicios\n"
                    + "2. Salario Integral\n"
                    + "3. Trabajo por Horas\n"
                    + "4. Salario Mensual\n"
                    + "5. Extralegales Pagados por la Empresa\n"
                    + "Cancelar: cerrar ventana";

            int option = lectura.readMenuOption(menu, -1);
            if (option<1 || option> 5) {
                escritura.mostrarMensaje("Registro de contrato cancelado.");
                return null;
            }

            switch (option) {
                case 1:
                    return "Prestacion de Servicios";
                case 2:
                    return "Salario Integral";
                case 3:
                    return "Trabajo por Horas";
                case 4:
                    return "Salario Mensual";
                case 5:
                    return "Extralegales Pagados por la Empresa";
                default:
                    escritura.mostrarMensaje("Has seleccionado una opcion invalida. Intenta de nuevo.");
                    break;
            }
        }while(true);
    }

    public static Contract selectContractFromList(ArrayList<Contract> contracts, String title, LecturaGUI lectura, EscrituraGUI escritura) {
       

        while (true) {
            String menu = title + "\n";
            for (int i = 0; i < contracts.size(); i++) {
                Contract contract = contracts.get(i);
                menu += (i + 1) + ". " + contract.getJob() + " | " + contract.getContractType() + "\n";
            }
            menu += "Cancelar: cerrar ventana";

            int option = lectura.readMenuOption(menu, -1);
            if (option == -1) {
                return null;
            }

            if (option >= 1 && option <= contracts.size()) {
                return contracts.get(option - 1);
            }

            escritura.mostrarMensaje("Seleccion invalida. Intenta de nuevo.");
        }
    }

    public boolean selectContractType() {
        String selectedType = requestContractType(this.lectura, this.escritura);
        if (selectedType == null) {
            return false;
        }

        this.contractType = selectedType;
        return true;
    }

    public boolean createContract() {
        String inputJob = lectura.leerString("Ingresa el cargo:");
        

        this.job = inputJob.trim();

        if (this.contractType == null || this.contractType.isBlank()) {
            boolean selected = selectContractType();
            if (!selected) {
                return false;
            }
        }

        // El registro solo guarda cargo y tipo de contrato.
        // La captura de horas/salario y la liquidacion se hacen en "Calcular salario".
        return true;
    }

    public void recalculatePayroll() {
        resetCalculatedValues();
        askSalaryData();
        calculateGrossSalary();
        calculateDeductions();
        calculateBonus();
        calculateNetSalary();
    }

    protected void resetCalculatedValues() {
        this.grossSalary = 0;
        this.totalDeductions = 0;
        this.totalBonus = 0;
        this.netSalary = 0;
        this.baseSalary = 0;
        this.healthInsurance = 0;
        this.pensionContribution = 0;
        this.solidarityFundContribution = 0;
        this.withholdingTax = 0;
        this.serviceBonus = 0;
        this.severancePay = 0;
    }

    public void calculateNetSalary() {
        this.netSalary = this.grossSalary + this.totalBonus - this.totalDeductions;
    }

    public String showPayrollDetail() {
        return "DETALLE DE NOMINA\n"
                + "Cargo: " + this.job + "\n"
                + "Tipo contrato: " + this.contractType + "\n"
                + "Salario bruto: $" + this.grossSalary + "\n"
                + "Salud: $" + this.healthInsurance + "\n"
                + "Pension: $" + this.pensionContribution + "\n"
                + "Fondo de solidaridad: $" + this.solidarityFundContribution + "\n"
                + "Retencion en la fuente: $" + this.withholdingTax + "\n"
                + "Total deducciones: $" + this.totalDeductions + "\n"
                + "Primas (Semestral): $" + this.serviceBonus + "\n"
                + "Cesantias (Anual): $" + this.severancePay+ "\n"
                + "Total bonificaciones: $" + this.totalBonus + "\n"
                + "Salario neto: $" + this.netSalary;
    }

    public abstract void askSalaryData();

    public abstract void calculateGrossSalary();

    public abstract void calculateDeductions();

    public abstract void calculateBonus();
}
