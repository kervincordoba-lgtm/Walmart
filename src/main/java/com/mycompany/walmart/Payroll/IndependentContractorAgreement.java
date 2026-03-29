/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.Payroll;

/**
 *
 * @author Kervin Cordoba
 */
public class IndependentContractorAgreement extends Contract{
    public IndependentContractorAgreement(){
        super();
        this.contractType = "Prestacion de Servicios";
    }
    
    @Override
    public void askSalaryData() {
        this.hoursWorked = lectura.leerInt("Ingresa las horas trabajadas:");
        this.hourlyPay = lectura.leerInt("Ingresa el valor de cada hora:");
    }

    @Override
    public void calculateGrossSalary() {
        
        this.grossSalary = this.hoursWorked * this.hourlyPay;
        if(this.grossSalary<=2*this.SMMLV){
            this.grossSalary += this.AUXILIO_TRANSPORTE;
        }
    }

    @Override
    public void calculateDeductions() {
        this.withholdingTax = this.grossSalary * 0.1;
        this.totalDeductions = this.withholdingTax;
        
    }
    
    @Override
    public void calculateBonus(){
        this.totalBonus = 0;
    }
}
