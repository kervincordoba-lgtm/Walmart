/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.Payroll;

/**
 *
 * @author Kervin Cordoba
 */
public class HourlyJob extends Contract{
    public HourlyJob(){
        super();
        this.contractType = "Trabajo por Horas";
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
        if(this.grossSalary<=2*this.SMMLV){
            this.grossSalary += this.AUXILIO_TRANSPORTE;
        }
    }

    @Override
    public void calculateDeductions() {
        if(this.grossSalary>2*this.SMMLV){
            this.healthInsurance = (this.grossSalary-this.AUXILIO_TRANSPORTE)*0.04;
            this.pensionContribution = (this.grossSalary-this.AUXILIO_TRANSPORTE)*0.04;
            
        }
        else{
            this.healthInsurance = this.grossSalary * 0.04;
            this.pensionContribution = this.grossSalary * 0.04;
            
        }
        this.totalDeductions = this.healthInsurance + this.pensionContribution;
        
    }
    
    @Override
    public void calculateBonus(){
        this.serviceBonus = this.grossSalary/2;
        this.severancePay = this.grossSalary;
        this.totalBonus = this.serviceBonus + this.severancePay;
    }
    
}
