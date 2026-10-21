/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public class AitBank {

    public static void main(String[] args) {
        testSavingsAccount();
        testNetSavingsAccount();
        testChequeAccount();
        testFixedAccount();
        testATM();    
    }
    public static void testSavingsAccount() {
        System.out.println("\n--- SAVINGS ACCOUNT ---");

        SavingsAccount savings = new SavingsAccount(
                "S001", "Alice", 1000, 0.01, 300);

        savings.showDetails();
        savings.deposit(200);
        savings.withdraw(250);
        savings.withdraw(100); // Goes above the daily limit

        savings.setDailyLimit(500);
        savings.resetDailyWithdrawals();
        savings.withdraw(400);

        savings.addInterest();
        savings.showDetails();
    }

    public static void testNetSavingsAccount() {
        System.out.println("\n--- NET SAVINGS ACCOUNT ---");

        NetSavingsAccount netSavings = new NetSavingsAccount(
                "N001", "Bob", 2000, 0.02, 500);

        netSavings.deposit(100);
        netSavings.withdraw(400);
        netSavings.withdraw(200); // Goes above the daily limit
        netSavings.addInterest();

        netSavings.showDetails();
    }

    public static void testChequeAccount() {
        System.out.println("\n--- CHEQUE ACCOUNT ---");

        ChequeAccount cheque = new ChequeAccount(
                "C001", "Charlie", 800);

        cheque.deposit(200);
        cheque.withdraw(900);
        cheque.addInterest();

        cheque.showDetails();
    }

    public static void testFixedAccount() {
        System.out.println("\n--- FIXED ACCOUNT ---");

        FixedAccount fixed = new FixedAccount(
                "F001", "Diana", 5000, 0.05);

        fixed.withdraw(100);
        fixed.addInterest();

        System.out.println(
                "Early withdrawal: " + fixed.hasEarlyWithdrawal());

        fixed.showDetails();

        System.out.println("\nFixed account without a withdrawal:");

        FixedAccount secondFixed = new FixedAccount(
                "F002", "Edward", 5000, 0.05);

        secondFixed.addInterest();
        secondFixed.showDetails();
    }

    public static void testATM() {
        System.out.println("\n-----------------ATM----------------");

        ATM atm = new ATM();

        ChequeAccount account = new ChequeAccount( "C002", "Fatima", 1000);
        System.out.println("--- Before ATM Withdrawn request ---");
        account.showDetails();
        System.out.println("\n------------------------------------");

        atm.withdraw(account, 70); // Valid: 50 + 20
        atm.withdraw(account, 30); // Invalid
        atm.withdraw(account, 100); // Valid
        System.out.println("\n------------------------------------");

        System.out.println("\n--- After ATM Withdrawn request ---");

        account.showDetails();
    }
}
