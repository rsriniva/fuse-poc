package org.jboss.poc.bean;

import java.util.Date;

public class CustomerTransaction {

    private Date date;
    private double amount;
    private String description;

    public CustomerTransaction() {
    }

    public CustomerTransaction(Date date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
