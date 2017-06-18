package com.nickas.tax;

import java.util.*;
import java.io.*;


public class Controller {

    private final File inputFile = new File("_input.csv");
    private final File outputFile = new File("_output.csv");

    private ArrayList employeeList = new ArrayList();

    public void calculate() {
        boolean loaded = loadInputFile();
        if (loaded) {
            employeeList = getEmployee();
            boolean done = writeOutputFile(employeeList);
            if (done) {
                System.out.println("Tax calculated! Result in _output.csv file.");
            } else {
                System.out.println("Error!");
            }
        }
    }

    //load up the employee file
    private boolean loadInputFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                String firstName = record[0].trim();
                String lastName = record[1].trim();
                String incomeString = record[2].trim();
                String rateString = record[3].trim();
                String period = record[4].trim();

                int income = Integer.parseInt(incomeString);
                Double rate = Double.parseDouble(rateString.replace("%", ""));

                Employee theEmployee = new Employee(firstName, lastName, income, rate, period);
                employeeList.add(theEmployee);
                line = reader.readLine();
            }

            reader.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ": " + e.getMessage());
            return false;
        }
    }

    private ArrayList getEmployee() {

        return employeeList;
    }

    // write calculation into an output file
    public boolean writeOutputFile(ArrayList localEmployeeList) {
        Iterator it = localEmployeeList.iterator();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            while (it.hasNext()) {
                Employee empl = (Employee) it.next();

                int grossIncome = (int) Math.round(empl.getIncome() / 12);
                int incomeTax = calculateIncomeTax(empl.getIncome());
                int netIncome = grossIncome - incomeTax;
                int employeeSuper = (int) Math.round(grossIncome * empl.getRate() / 100);
                writer.write(empl.getFirstName() + " " + empl.getLastName() + ","
                        + empl.getPeriod() + "," + grossIncome + "," + incomeTax + ","
                        + netIncome + "," + employeeSuper + "\r\n");
            }

            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ": " + e.getMessage());
            return false;
        }
    }

    public int calculateIncomeTax(int income) {
        if (income < 18201) {
            return 0;
        } else if (income > 18200 && income < 37001) {
            return (int) Math.round(((income - 18200) * 0.19) / 12);
        } else if (income > 37000 && income < 80001) {
            return (int) Math.round((3572 + (income - 37000) * 0.325) / 12);
        } else if (income > 80000 && income < 180001) {
            return (int) Math.round((17547 + (income - 80000) * 0.37) / 12);
        } else {
            return (int) Math.round((54547 + (income - 180000) * 0.45) / 12);
        }
    }
}
