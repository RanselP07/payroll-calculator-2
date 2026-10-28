package com.pluralsight;

public class Employee {

    private int employeeID;
    private String name;
    private double hoursWorked;
    private double payRate;
    private double grossPay = hoursWorked * payRate;

    Employee(int employeeID, String name, double hoursWorked, double payRate, double grossPay){
        this.employeeID = employeeID;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
        this.grossPay = grossPay;
    }

    //region Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getGrossPay(){
        return grossPay;
    }

    public void setGrossPay(double grossPay){
        this.grossPay = grossPay;
    }
    //endregion

}
