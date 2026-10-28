package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class PayrollCalc2 {

    static void main(String[] args) {

        String fileName = "src/main/resources/employees.csv";

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
            String line = bufReader.readLine();
            line = bufReader.readLine();

            while (line != null){
                Employee employee = parseEmployee(line);
                System.out.printf("%d %s %f \n", employee.getEmployeeID(), employee.getName(), employee.getGrossPay());

                line = bufReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Employee parseEmployee(String employeeCsv) {
        String[] lineSplit = employeeCsv.split(Pattern.quote("|"));

        int id = Integer.parseInt(lineSplit[0]);
        String name = lineSplit[1];
        double hoursWorked = Double.parseDouble(lineSplit[2]);
        double payRate = Double.parseDouble(lineSplit[3]);
        double grossPay = hoursWorked * payRate;

        Employee employee = new Employee(id, name, hoursWorked, payRate, grossPay);

        return employee;
    }

}
