/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public class SavingsAccount extends Account {
    private double dailyLimit;
    private double withdrawnToday;

    public SavingsAccount(String accountNumber, String customerName, double balance, double interestRate, double dailyLimit) {
        //super(...) calls the constructor of the parent class.
        super(accountNumber, customerName, balance, interestRate); //This line calls the constructor in Account
        this.dailyLimit = dailyLimit;
        this.withdrawnToday = 0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (!canWithdraw(amount)) {
            System.out.println("Withdrawal failed.");
            return false;
        }

        if (withdrawnToday + amount > dailyLimit) {
            System.out.println("Daily withdrawal limit exceeded.");
            return false;
        }

        balance -= amount;
        withdrawnToday += amount;

        System.out.println("$" + amount + " withdrawn.");
        return true;
    }

    @Override
    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;

        System.out.println("$" + interest + " interest added.");
    }

    public void setDailyLimit(double dailyLimit) {
        if (dailyLimit > 0) {
            this.dailyLimit = dailyLimit;
        }
    }

    public void resetDailyWithdrawals() {
        withdrawnToday = 0;
    }
}


