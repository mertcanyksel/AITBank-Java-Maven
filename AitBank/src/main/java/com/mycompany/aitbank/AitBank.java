package com.mycompany.aitbank;

/**
 * Runs simple automated tests for every class in the AITBank model.
 *
 * Replace the current contents of AitBank.java with this code. If you do that,
 * keep the class name as AitBank and rename this file to AitBank.java.
 *
 * These tests do not require JUnit. Each check prints PASS or FAIL, making the
 * file suitable for a beginner-level console project.
 */
public class AitBank {

    private static int passedTests = 0;
    private static int failedTests = 0;

    /**
     * Runs all test groups and prints the final result.
     */
    public static void main(String[] args) {
        testCommonAccountMethods();
        testSavingsAccount();
        testNetSavingsAccount();
        testChequeAccount();
        testFixedAccount();
        testATM();

        System.out.println("\n==============================");
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("==============================");
    }

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

    /**
     * Tests interest, the customer-controlled limit, cumulative withdrawals,
     * rejected withdrawals and the daily reset operation.
     */
    public static void testSavingsAccount() {
        printHeading("SAVINGS ACCOUNT");

        SavingsAccount account = new SavingsAccount(
                "S100", "Bella", 1000, 0.01, 300);

        check("Savings accepts a valid withdrawal", account.withdraw(200));
        checkAmount("Savings deducts a valid withdrawal", 800, account.balance);

        check("Savings can reach its exact daily limit", account.withdraw(100));
        check("Savings rejects amount above daily limit", !account.withdraw(1));
        checkAmount("Rejected savings withdrawal does not change balance",
                700, account.balance);

        account.resetDailyWithdrawals();
        check("Daily reset allows another withdrawal", account.withdraw(300));

        SavingsAccount changeableLimit = new SavingsAccount(
                "S101", "Bella", 1000, 0.01, 100);
        check("Original savings limit rejects $150",
                !changeableLimit.withdraw(150));
        changeableLimit.setDailyLimit(200);
        check("Customer can increase savings limit",
                changeableLimit.withdraw(150));

        SavingsAccount invalidLimit = new SavingsAccount(
                "S102", "Bella", 1000, 0.01, 100);
        invalidLimit.setDailyLimit(-50);
        check("Negative savings limit is ignored",
                !invalidLimit.withdraw(150));

        SavingsAccount interestAccount = new SavingsAccount(
                "S103", "Bella", 1000, 0.01, 300);
        interestAccount.addInterest();
        checkAmount("Savings adds 1% interest", 1010, interestAccount.balance);

        SavingsAccount insufficientFunds = new SavingsAccount(
                "S104", "Bella", 100, 0.01, 300);
        check("Savings rejects insufficient funds",
                !insufficientFunds.withdraw(150));
        check("Savings rejects zero withdrawal",
                !insufficientFunds.withdraw(0));
        check("Savings rejects negative withdrawal",
                !insufficientFunds.withdraw(-20));
    }

    /**
     * Tests the fixed daily limit, cumulative withdrawals, daily reset and
     * monthly-interest operation of a net savings account.
     */
    public static void testNetSavingsAccount() {
        printHeading("NET SAVINGS ACCOUNT");

        NetSavingsAccount account = new NetSavingsAccount(
                "N100", "Chris", 2000, 0.02, 500);

        check("Net savings accepts first withdrawal", account.withdraw(400));
        check("Net savings can reach exact daily limit", account.withdraw(100));
        check("Net savings rejects amount above daily limit", !account.withdraw(1));
        checkAmount("Rejected net savings withdrawal keeps balance",
                1500, account.balance);

        account.resetDailyWithdrawals();
        check("Net savings daily reset works", account.withdraw(500));

        NetSavingsAccount interestAccount = new NetSavingsAccount(
                "N101", "Chris", 2000, 0.02, 500);
        interestAccount.addInterest();
        checkAmount("Net savings adds 2% interest", 2040, interestAccount.balance);

        NetSavingsAccount insufficientFunds = new NetSavingsAccount(
                "N102", "Chris", 100, 0.02, 500);
        check("Net savings rejects insufficient funds",
                !insufficientFunds.withdraw(150));
        check("Net savings rejects zero withdrawal",
                !insufficientFunds.withdraw(0));
    }

    /**
     * Tests unrestricted withdrawals, insufficient funds and the rule that
     * cheque accounts do not earn interest.
     */
    public static void testChequeAccount() {
        printHeading("CHEQUE ACCOUNT");

        ChequeAccount account = new ChequeAccount("C200", "Dina", 2000);

        check("Cheque accepts first withdrawal", account.withdraw(700));
        check("Cheque has no daily limit", account.withdraw(800));
        checkAmount("Cheque withdrawals update balance", 500, account.balance);

        check("Cheque rejects insufficient funds", !account.withdraw(501));
        check("Cheque rejects zero withdrawal", !account.withdraw(0));
        check("Cheque rejects negative withdrawal", !account.withdraw(-50));
        checkAmount("Rejected cheque withdrawals keep balance", 500, account.balance);

        account.addInterest();
        checkAmount("Cheque addInterest leaves balance unchanged",
                500, account.balance);
    }

    /**
     * Tests interest before withdrawal, successful early withdrawal and the
     * rule that a failed withdrawal does not count as an early withdrawal.
     */
    public static void testFixedAccount() {
        printHeading("FIXED ACCOUNT");

        FixedAccount completedContract = new FixedAccount(
                "F100", "Evan", 1000, 0.05);
        check("New fixed account has no early withdrawal",
                !completedContract.hasEarlyWithdrawal());
        completedContract.addInterest();
        checkAmount("Fixed account adds 5% interest when eligible",
                1050, completedContract.balance);

        FixedAccount earlyAccount = new FixedAccount(
                "F101", "Evan", 1000, 0.05);
        check("Fixed account permits withdrawal", earlyAccount.withdraw(100));
        check("Successful withdrawal sets early-withdrawal flag",
                earlyAccount.hasEarlyWithdrawal());
        earlyAccount.addInterest();
        checkAmount("Early fixed withdrawal prevents interest",
                900, earlyAccount.balance);

        FixedAccount failedWithdrawal = new FixedAccount(
                "F102", "Evan", 1000, 0.05);
        check("Fixed account rejects insufficient funds",
                !failedWithdrawal.withdraw(1500));
        check("Failed withdrawal does not set early-withdrawal flag",
                !failedWithdrawal.hasEarlyWithdrawal());
        failedWithdrawal.addInterest();
        checkAmount("Interest remains available after failed withdrawal",
                1050, failedWithdrawal.balance);
    }

    /**
     * Tests ATM note combinations as well as successful, invalid and
     * insufficient-funds ATM withdrawals.
     */
    public static void testATM() {
        printHeading("ATM");

        ATM atm = new ATM();

        check("ATM rejects negative amount", !atm.canGiveAmount(-20));
        check("ATM rejects zero", !atm.canGiveAmount(0));
        check("ATM rejects $10", !atm.canGiveAmount(10));
        check("ATM accepts $20", atm.canGiveAmount(20));
        check("ATM rejects $30", !atm.canGiveAmount(30));
        check("ATM accepts $40", atm.canGiveAmount(40));
        check("ATM accepts $50", atm.canGiveAmount(50));
        check("ATM accepts $60", atm.canGiveAmount(60));
        check("ATM accepts $70", atm.canGiveAmount(70));
        check("ATM rejects $75", !atm.canGiveAmount(75));
        check("ATM accepts $100", atm.canGiveAmount(100));
        check("ATM accepts $110", atm.canGiveAmount(110));

        ChequeAccount validAccount = new ChequeAccount("C300", "Farah", 500);
        check("ATM completes valid $70 withdrawal",
                atm.withdraw(validAccount, 70));
        checkAmount("ATM withdrawal updates account balance",
                430, validAccount.balance);

        ChequeAccount invalidNotes = new ChequeAccount("C301", "Farah", 500);
        check("ATM rejects amount it cannot dispense",
                !atm.withdraw(invalidNotes, 30));
        checkAmount("Invalid ATM amount keeps balance",
                500, invalidNotes.balance);

        ChequeAccount insufficientFunds = new ChequeAccount(
                "C302", "Farah", 500);
        check("ATM rejects withdrawal above account balance",
                !atm.withdraw(insufficientFunds, 600));
        checkAmount("Failed ATM withdrawal keeps balance",
                500, insufficientFunds.balance);
    }

    /**
     * Records the result of a true/false test.
     */
    private static void check(String testName, boolean condition) {
        if (condition) {
            System.out.println("PASS: " + testName);
            passedTests++;
        } else {
            System.out.println("FAIL: " + testName);
            failedTests++;
        }
    }

    /**
     * Compares monetary values with a small tolerance to avoid problems caused
     * by the way double values are represented internally.
     */
    private static void checkAmount(String testName,
                                    double expected,
                                    double actual) {
        double tolerance = 0.001;
        check(testName, Math.abs(expected - actual) < tolerance);

        if (Math.abs(expected - actual) >= tolerance) {
            System.out.println("      Expected: " + expected
                    + ", actual: " + actual);
        }
    }

    /**
     * Separates test groups in the console output.
     */
    private static void printHeading(String heading) {
        System.out.println("\n--- " + heading + " ---");
    }
}
