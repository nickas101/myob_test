package com.nickas.tax;

public class Employee {

    private String firstName;
    private String lastName;
    private Integer income;
    private Double rate;
    private String period;

    // default constructor
    public Employee() {
        firstName = "";
        lastName = "";
        income = 0;
        rate = 0.0;
        period = "";
    }

    // constructor with arguments
    public Employee(String firstName, String lastName, Integer income, Double rate, String period) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.income = income;
        this.rate = rate;
        this.period = period;
    }

    //setter methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    //getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getIncome() {
        return income;
    }

    public Double getRate() {
        return rate;
    }

    public String getPeriod() {
        return period;
    }
}
