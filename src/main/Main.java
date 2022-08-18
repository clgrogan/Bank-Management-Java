package src.main;

import java.util.concurrent.TimeUnit;

import src.main.model.account.Account;
import src.main.model.account.Checking;
import src.main.model.account.Loan;
import src.main.model.account.Savings;

public class Main {

    static String ACCOUNTS_FILE = "src/main/data/accounts.txt";
    static String TRANSACTIONS_FILE = "src/main/data/transactions.txt";

    public static void main(String[] args) {
        Account[] accounts = new Account[] {
                new Checking("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51),
                new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60),
                new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", 2537.31) };
        double withdrawalRate = 0.02;
        double initialBalance = accounts[2].getBalance();
        double amount = 1000;

        Checking checking = new Checking(
                "f84c43f4-a634-4c57-a644-7602f8840870",
                "Michael Scott",
                1524.51);
        Savings savings = new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60);
        Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Mike Myers", 2537.31);

        System.out.println();

        System.out.println(checking);
        System.out.println(loan);
        System.out.println(savings);
        accounts[2].withdrawal(amount);
        System.out.println("\ninitialBalance: " + initialBalance
                + "\n(initialBalance + amount * (1 + withdrawalRate)): "
                + (initialBalance + amount * (1 + withdrawalRate))
                + "\namount * (1 + withdrawalRate): " + amount * (1 + withdrawalRate)
                + "\naccounts[2].getBalance(): " + accounts[2].getBalance());

        System.out.println();
    }

    /**
     * Function name: wait
     * 
     * @param milliseconds
     * 
     *                     Inside the function:
     *                     1. Makes the code sleep for X milliseconds.
     */

    public static void wait(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
