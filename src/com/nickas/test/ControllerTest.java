package com.nickas.test;

import com.nickas.tax.Controller;
import com.nickas.tax.Employee;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test // Check that income tax is calculated properly
    public void testTaxCalculation() throws Exception {
        Controller taxCalculate = new Controller();
        int expectedIncomeTax = taxCalculate.calculateIncomeTax(60050);

        assertEquals(922, expectedIncomeTax);
    }

    @Test // Check that employee data has calculated and written into a file properly
    public void testWritingFile() throws Exception {
        Controller calculateForFile = new Controller();
        ArrayList employeeList = new ArrayList();

        Employee theEmployee = new Employee(
                "David",
                "Rudd",
                60050,
                9.0,
                "01 March – 31 March"
        );
        employeeList.add(theEmployee);

        boolean fileHasWritten = calculateForFile.writeOutputFile(employeeList);

        assertEquals(true, fileHasWritten);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("_output.csv"));
            String line = reader.readLine();

            assertEquals("David Rudd,01 March – 31 March,5004,922,4082,450", line);

            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString() + ": " + e.getMessage());
        }
    }

}
