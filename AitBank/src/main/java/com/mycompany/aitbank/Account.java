/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public abstract class Account {
    protected String accountNumber;
    protected String customerName;
    protected double balance;
    protected double interestRate;

    public Account(String accountNumber, String customerName,
                   double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("$" + amount + " deposited.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean canWithdraw(double amount) {
        return amount > 0 && amount <= balance;
    }

    public abstract boolean withdraw(double amount);

    public abstract void addInterest();

    public void showDetails() {
        System.out.println("Account number: " + accountNumber);
        System.out.println("Customer: " + customerName);
        System.out.println("Balance: $" + balance);
    }
}

