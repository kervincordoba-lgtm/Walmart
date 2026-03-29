/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.Payroll;

/**
 *
 * @author Kervin Cordoba
 */
public class MonthlySalary extends Contract {
    public MonthlySalary(){
        super();
        this.contractType = "Salario Mensual";
    }
    
    @Override
    public void askSalaryData() {
        this.hoursWorked = lectura.leerInt("Ingresa las horas trabajadas:");
        this.hourlyPay = lectura.leerInt("Ingresa el valor de cada hora:");
        
        this.dayOvertimeHours = lectura.leerInt("Ingresa las horas extras trabajadas en el día:");
        this.nightOvertimeHours = lectura.leerInt("Ingresa las horas extras trabajadas en la noche y/o días festivos:");
    }

    @Override
    public void calculateGrossSalary() {
        this.grossSalary = this.hoursWorked * this.hourlyPay + this.dayOvertimeHours * this.hourlyPay * 1.25 + this.nightOvertimeHours * this.hourlyPay * 1.75;
        this.baseSalary = this.grossSalary;
        if(this.grossSalary<=2*this.SMMLV){
            this.grossSalary += this.AUXILIO_TRANSPORTE;
        }
    }

    @Override
    public void calculateDeductions() {
        if(this.grossSalary>2*this.SMMLV){
            this.healthInsurance = this.baseSalary*0.04;
            this.pensionContribution = this.baseSalary*0.04;
            if(this.grossSalary>=4*this.SMMLV){
                this.solidarityFundContribution = this.baseSalary*0.01;
            }
        }
        else{
            this.healthInsurance = this.grossSalary * 0.04;
            this.pensionContribution = this.grossSalary * 0.04;
        }
       
        this.totalDeductions = this.healthInsurance + this.pensionContribution + this.solidarityFundContribution;
        if((this.baseSalary-this.totalDeductions)>95*UVT){
                this.withholdingTax= (this.baseSalary-this.totalDeductions)*0.19;
            }
        this.totalDeductions += this.withholdingTax;
    }
    
    @Override
    public void calculateBonus(){
        this.serviceBonus = this.grossSalary/2;
        this.severancePay = this.grossSalary;
        this.totalBonus = this.serviceBonus + this.severancePay;
    }
    
}
