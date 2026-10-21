/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public class ATM {

//    public boolean withdraw(Account account, int amount) {
//        if (!canGiveAmount(amount)) {
//            System.out.println("ATM cannot give this amount using $20, $50 and $100 notes.");
//            return false;
//        }
//
//        return account.withdraw(amount);
//    }
//
//    public boolean canGiveAmount(int amount) {
//        if (amount <= 0) {
//            return false;
//        }
//
//        // Valid examples: 20, 40, 50, 60, 70, 80, 90, 100...
//        return amount == 20 || amount == 40 || (amount >= 50 && amount % 10 == 0);
//    }
    
    public boolean withdraw(Account account, int amount) {
        if (!canGiveAmount(amount)) {
            System.out.println(
                    "ATM cannot give this amount using $20, $50 and $100 notes.");
            return false;
        }

        return account.withdraw(amount);
    }

    public boolean canGiveAmount(int amount) {
        if (amount <= 0) {
            return false;
        }

        for (int twenties = 0;
             twenties * 20 <= amount;
             twenties++) {

            int remainingAmount =
                    amount - (twenties * 20);

            if (remainingAmount % 50 == 0) {
                return true;
            }
        }

        return false;
    }
    
}
