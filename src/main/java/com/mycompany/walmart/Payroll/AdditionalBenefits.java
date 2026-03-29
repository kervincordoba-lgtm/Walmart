/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.Payroll;

/**
 *
 * @author Kervin Cordoba
 */
public class AdditionalBenefits extends Contract {
        public AdditionalBenefits(){
        super();
        this.contractType = "Extralegales Pagados por la Empresa";
    }
    
    @Override
    public void askSalaryData() {
        this.hoursWorked = lectura.leerInt("Ingresa las horas trabajadas:");
        this.hourlyPay = lectura.leerInt("Ingresa el valor de cada hora:");
    }

    @Override
    public void calculateGrossSalary() {
        
        this.grossSalary = this.hoursWorked * this.hourlyPay;
        this.baseSalary = this.grossSalary;
        if(this.grossSalary<=2*this.SMMLV){
            this.grossSalary += this.AUXILIO_TRANSPORTE;
        }
    }

    @Override
    public void calculateDeductions() {
        
        this.withholdingTax = this.baseSalary*0.19;
        this.totalDeductions = this.withholdingTax;
        
    }
    
    @Override
    public void calculateBonus(){
        this.totalBonus = 0;
    }
    
}
