/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public class ChequeAccount extends Account{
    public ChequeAccount(String accountNumber, String customerName,double balance) {
        super(accountNumber, customerName, balance, 0);
        //Cheque account offers "no interest", and it also has no daily withdrawal limit.
    }

    @Override
    public boolean withdraw(double amount) {
        if (!canWithdraw(amount)) {
            System.out.println("Withdrawal failed.");
            return false;
        }

        balance -= amount;

        System.out.println("$" + amount + " withdrawn.");
        return true;
    }

    @Override
    public void addInterest() {
        System.out.println("Cheque accounts do not receive interest.");
    }
}
