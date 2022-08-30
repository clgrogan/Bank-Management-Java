package src.main;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import src.main.model.Bank;
import src.main.model.Transaction;
import src.main.model.account.Account;
import src.main.model.account.Checking;
import src.main.model.account.Loan;
import src.main.model.account.Savings;

public class Main {

    static String ACCOUNTS_FILE = "src/main/data/accounts.txt";
    static String TRANSACTIONS_FILE = "src/main/data/transactions.txt";

    public static void main(String[] args) {
        mainAccount();
        mainBank();

    }

    private static void mainBank() {
        Bank bank = new Bank();
        Account[] accounts = new Account[] {
                new Checking("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott",
                        new BigDecimal(Double.valueOf(1524.51))),
                new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman",
                        new BigDecimal(Double.valueOf(2241.60)))
        };

        for (Account account : accounts) {
            bank.addAccount(account);
        }
        System.out.println("\n - - - Transactions Start - - -\n");
        Transaction[] transactions = new Transaction[] {
                new Transaction(Transaction.Type.WITHDRAW, 1609459200, "ce07d7b3-9038-43db-83ae-77fd9c0450c9",
                        new BigDecimal(Double.valueOf(917.21))),
                new Transaction(Transaction.Type.WITHDRAW, 1546905600, "f84c43f4-a634-4c57-a644-7602f8840870",
                        new BigDecimal(Double.valueOf(624.99))),
                new Transaction(Transaction.Type.DEPOSIT, 1578700800, "f84c43f4-a634-4c57-a644-7602f8840870",
                        new BigDecimal(Double.valueOf(441.93))),
                new Transaction(Transaction.Type.WITHDRAW, 1547078400, "f84c43f4-a634-4c57-a644-7602f8840870",
                        new BigDecimal(Double.valueOf(546.72))),
                new Transaction(Transaction.Type.DEPOSIT, 1578355200, "f84c43f4-a634-4c57-a644-7602f8840870",
                        new BigDecimal(Double.valueOf(635.95))),
                new Transaction(Transaction.Type.WITHDRAW, 1547078400, "ce07d7b3-9038-43db-83ae-77fd9c0450c9",
                        new BigDecimal(Double.valueOf(875.64))),
                new Transaction(Transaction.Type.WITHDRAW, 1578614400, "ce07d7b3-9038-43db-83ae-77fd9c0450c9",
                        new BigDecimal(Double.valueOf(912.45))),
                new Transaction(Transaction.Type.WITHDRAW, 1577836800, "ce07d7b3-9038-43db-83ae-77fd9c0450c9",
                        new BigDecimal(Double.valueOf(695.09))),
                new Transaction(Transaction.Type.WITHDRAW, 1578096000, "ce07d7b3-9038-43db-83ae-77fd9c0450c9",
                        new BigDecimal(Double.valueOf(127.94))),
                new Transaction(Transaction.Type.WITHDRAW, 1546732800, "f84c43f4-a634-4c57-a644-7602f8840870",
                        new BigDecimal(Double.valueOf(546.72))),
                new Transaction(Transaction.Type.WITHDRAW, 1546819200, "ce07d7b3-9038-43db-83ae-77fd9c0450c9",
                        new BigDecimal(Double.valueOf(612.52))),
        };

        for (Transaction transaction : transactions) {
            // bank.addTransaction(transaction); // privatized
        }
        System.out.println("All Bank Transactions");
        Transaction[] bankTransactions = bank.getTransactions();
        for (int i = 0; i < bankTransactions.length; i++) {
            System.out.println("\t" + bankTransactions[i]);
        }
        Transaction[] filteredTransactions = bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870");
        System.out.println("\nBank Transactions filtered to an Account ID");
        for (int i = 0; i < filteredTransactions.length; i++) {
            System.out.println("\t" + filteredTransactions[i]);
        }
        System.out.println("\n - - - Transactions End - - -");
        System.out.println();
        System.out.println("Account account = bank.getAccount(\"ce07d7b3-9038-43db-83ae-77fd9c0450c9\")");
        Account account = bank.getAccount("ce07d7b3-9038-43db-83ae-77fd9c0450c9");
        System.out.println("Returns the account");
        System.out.println("\t" + account);
        System.out.println();
    }

    private static void mainAccount() {

        Checking checking = new Checking(
                "f84c43f4-a634-4c57-a644-7602f8840870",
                "Michael Scott",
                new BigDecimal(Double.valueOf(1524.51)));
        Savings savings = new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman",
                new BigDecimal(Double.valueOf(2241.60)));
        Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Mike Myers",
                new BigDecimal(Double.valueOf(2537.31)));

        System.out.println();
        Account checkingClone = checking.clone();
        Account savingsClone = savings.clone();
        Account loanClone = loan.clone();

        System.out.println("checking: " + checking);
        System.out.println("checkingClone: " + checkingClone);
        System.out.println("\tchecking == checkingClone:     " + (checking == checkingClone));
        System.out.println("\tchecking.equals(checkingClone: " + (checking.equals(checkingClone)));
        System.out.println("loan: " + loan);
        System.out.println("loanClone: " + loanClone);
        System.out.println("savings: " + savings);
        System.out.println("savingsClone: " + savingsClone);

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
