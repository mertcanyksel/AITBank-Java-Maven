/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aitbank;

/**
 *
 * @author mertcanyuksel
 */
public class testCommonAccountMethods {
    /**
     * Tests methods inherited from Account by using a ChequeAccount object.
     */
    public static void testCommonAccountMethods() {
        printHeading("COMMON ACCOUNT METHODS");

        ChequeAccount account = new ChequeAccount("C100", "Alex", 1000);

        checkAmount("Constructor stores opening balance", 1000, account.balance);

        account.deposit(250);
        checkAmount("Positive deposit increases balance", 1250, account.balance);

        account.deposit(0);
        checkAmount("Zero deposit is rejected", 1250, account.balance);

        account.deposit(-100);
        checkAmount("Negative deposit is rejected", 1250, account.balance);

        check("canWithdraw accepts a valid amount", account.canWithdraw(200));
        check("canWithdraw accepts the complete balance", account.canWithdraw(1250));
        check("canWithdraw rejects an amount above balance", !account.canWithdraw(1251));
        check("canWithdraw rejects zero", !account.canWithdraw(0));
        check("canWithdraw rejects a negative amount", !account.canWithdraw(-10));

        // This is a display method, so visually check the three printed values.
        System.out.println("Manual display check follows:");
        account.showDetails();
    }

    private static void printHeading(String common_account_methods) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void checkAmount(String constructor_stores_opening_balance, int i, double balance) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void check(String canWithdraw_accepts_a_valid_amount, boolean canWithdraw) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
