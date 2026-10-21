/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public class FixedAccount extends Account{
    private boolean earlyWithdrawal;

    public FixedAccount(String accountNumber, String customerName, double balance, double interestRate) {
        super(accountNumber, customerName, balance, interestRate);
        this.earlyWithdrawal = false;
    }

    @Override
    public boolean withdraw(double amount) {
        if (!canWithdraw(amount)) {
            System.out.println("Withdrawal failed.");
            return false;
        }

        balance -= amount;
        earlyWithdrawal = true;

        System.out.println("$" + amount + " withdrawn early.");
        return true;
    }

    @Override
    public void addInterest() {
        if (earlyWithdrawal) {
            System.out.println(
                    "No interest because an early withdrawal was made.");
            return;
        }

        double interest = balance * interestRate;
        balance += interest;

        System.out.println("$" + interest + " interest added.");
    }

    public boolean hasEarlyWithdrawal() {
        return earlyWithdrawal;
    }
}
