package com.mycompany.walmart.Payroll;

public class ComprehensiveContract extends Contract {

    public ComprehensiveContract() {
        super();
        this.contractType = "Salario Integral";
    }

    @Override
    public void askSalaryData() {
        this.baseSalary = lectura.leerDoble("Ingresa el salario base integral:");

        while (this.baseSalary < 13 * SMMLV) {
            this.baseSalary = lectura.leerDoble("Un salario integral no puede ser inferior a 13 SMMLV. Intenta de nuevo:");
        }
    }

    @Override
    public void calculateGrossSalary() {
        this.grossSalary = this.baseSalary;
    }

    @Override
    public void calculateDeductions() {
        // Para salario integral, salud y pension se calculan sobre el 70% del salario.
        this.healthInsurance = this.grossSalary * 0.7 * 0.04;
        this.pensionContribution = this.grossSalary * 0.7 * 0.04;
        this.solidarityFundContribution = this.grossSalary * 0.01;
        
        
        this.totalDeductions = this.healthInsurance + this.pensionContribution + this.solidarityFundContribution + this.withholdingTax;
        this.withholdingTax = (this.grossSalary - this.totalDeductions)*0.19; //La minima de la retencion de la fuente
        this.totalDeductions += this.withholdingTax;
    }

    @Override
    public void calculateBonus() {
        this.totalBonus = 0;
    }
}
