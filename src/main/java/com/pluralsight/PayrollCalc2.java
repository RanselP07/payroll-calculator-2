package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class PayrollCalc2 {

    static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        int counter = -1;

        System.out.print("Enter the name of the file to process: ");
        String inputFile = myScanner.nextLine().trim().toLowerCase();
        String fileName = "src/main/resources/" + inputFile;

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFile = myScanner.nextLine().trim().toLowerCase();
        String[] fileSplit = outputFile.split("\\.");
        String fileType = fileSplit[1];


        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
            String line = bufReader.readLine();
            line = bufReader.readLine();

            ArrayList<Integer> id = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            ArrayList<Double> pay = new ArrayList<>();

            while (line != null){
                Employee employee = parseEmployee(line);

                System.out.printf("%d %s %f \n", employee.getEmployeeID(), employee.getName(), employee.getGrossPay());

                id.add(employee.getEmployeeID());
                name.add(employee.getName());
                pay.add(employee.getGrossPay());

                counter++;

                line = bufReader.readLine();
            }

            try {
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter(outputFile));

                if (fileType.equals("csv")) {
                    for (int i = 0; i < counter; i++) {
                        String text = "id :" + id.get(i) + ", name: " + name.get(i) + ", grossPay: " + pay.get(i) + "\n";
                        bufWriter.write(text);
                    }
                    bufWriter.close();
                }else if (fileType.equals("json")){
                    bufWriter.write("[\n");

                    for (int i = 0; i < counter; i++) {

                        if (i == 6){
                            String text = "{ \"id\": " + id.get(i) + ", \"name\" : \"" + name.get(i) + "\", \"grossPay\" : " + pay.get(i) + "}\n";
                            bufWriter.write(text);
                        } else {
                            String text = "{ \"id\": " + id.get(i) + ", \"name:\" : \"" + name.get(i) + "\", \"grossPay\" : " + pay.get(i) + "},\n";
                            bufWriter.write(text);
                        }
                    }

                    bufWriter.write("]");
                    bufWriter.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();

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
