package src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import src.main.model.Bank;
import src.main.model.Transaction;
import src.main.model.account.Account;
import src.main.model.account.Checking;
import src.main.model.account.Loan;
import src.main.model.account.Savings;

public class MainFinal {

    static String ACCOUNTS_FILE = "src/main/data/accounts.txt";
    static String TRANSACTIONS_FILE = "src/main/data/transactions.txt";
    private static Bank bigBank = new Bank();

    public static void main(String[] args) {
        ArrayList<Account> accounts = null;
        ArrayList<Transaction> transactions = null;

        try {
            accounts = returnAccounts();
            loadAccounts(accounts);
            transactions = returnTransactions();
            runTransactions(transactions);
        } catch (FileNotFoundException e) {
            System.out.println("The wheels fell off: " + e.getMessage());
            e.printStackTrace();
        }

        for (Account account : bigBank.accounts) {
            System.out.println("\n\t\t\t\t\t ACCOUNT\n\n\t" + account + "\n\n");
            transactionHistory(account.getId());
        }

    }

    /**
     * Name: createAccount
     * 
     * @param values (String[] values)
     * @return Account
     * 
     *         Inside the function:
     *         1. Dynamically creates a Checking, Loan, or Savings object based on
     *         the values array.
     */

    public static Account createAccount(String[] values) {

        switch (values[0]) {
            case "Checking":
                return new Checking(values[1], values[2], Double.parseDouble(values[3]));
            case "Savings":
                return new Savings(values[1], values[2], Double.parseDouble(values[3]));
            case "Loan":
                return new Loan(values[1], values[2], Double.parseDouble(values[3]));
            default:
                return null;
        }
    }

    /**
     * Name: returnAccounts()
     * 
     * @return ArrayList<Account>
     * @throws FileNotFoundException
     * 
     *                               Inside the function:
     *                               1. Creates a Scanner object and reads the data
     *                               from accounts.txt.
     *                               2. Creates an Account object for every line in
     *                               accounts.txt.
     *                               3. Returns an ArrayList of Account objects.
     */

    public static ArrayList<Account> returnAccounts() throws FileNotFoundException {
        ArrayList<Account> accounts = new ArrayList<Account>();

        FileInputStream fis = new FileInputStream(ACCOUNTS_FILE);
        Scanner scan = new Scanner(fis);

        while (scan.hasNextLine()) {
            accounts.add(createAccount(scan.nextLine().split(",")));
        }

        scan.close();

        return accounts;
    }

    /**
     * Name: loadAccounts
     * 
     * @param accounts (ArrayList<Account>)
     * 
     *                 Inside the function:
     *                 1. Loads every account into the Bank object.
     * 
     */
    public static void loadAccounts(ArrayList<Account> accounts) {
        accounts.stream().forEach((account) -> {
            bigBank.addAccount(account);
        });
    }

    public static Transaction createTransaction(String[] values) {
        return new Transaction(Transaction.Type.valueOf(values[1]), Long.parseLong(values[0]), values[2],
                Double.parseDouble(values[3]));
    }

    /**
     * Name: returnTransactions()
     * 
     * @return ArrayList<Transaction>
     * @throws FileNotFoundException
     * 
     *                               Inside the function:
     *                               1. Creates a Scanner object and reads the data
     *                               from transactions.txt.
     *                               2. Populates an ArrayList with transaction
     *                               objects.
     *                               3. Sorts the ArrayList.
     */

    public static ArrayList<Transaction> returnTransactions() throws FileNotFoundException {

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        FileInputStream fis = new FileInputStream(TRANSACTIONS_FILE);
        Scanner scan = new Scanner(fis);

        while (scan.hasNextLine()) {
            transactions.add(createTransaction(scan.nextLine().split(",")));
        }
        scan.close();
        Collections.sort(transactions);
        return transactions;
    }

    /**
     * Name: runTransactions
     * 
     * @param transactions ArrayList<Transaction>
     * 
     *                     Inside the function:
     *                     1. Executes every transaction using bank.execute.
     */
    public static void runTransactions(ArrayList<Transaction> transactions) {
        transactions.stream()
                .forEach((transaction) -> bigBank.executeTransaction(transaction));
    }

    /**
     * Name: transactionHistory
     * 
     * @param accountId (String)
     *                  <p>
     *                  Inside the function<br>
     *                  1. Print: "\t\t\t\t TRANSACTION HISTORY\n\t"<br>
     *                  2. Print every transaction that corresponds to the id.<br>
     *                  (Waits x milliseconds between lines)
     *                  - Use this format "\t"+transaction+"\n"<br>
     *                  3. Print: "\n\t\t\t\t\tAFTER TAX\n"<br>
     *                  4. Print: "\t" + account that corresponds to id
     *                  +"\n\n\n\n"
     */
    public static void transactionHistory(String accountId) {
        Transaction[] accountTransactions = bigBank.getTransactions(accountId);
        System.out.println("\t\t\t\t   TRANSACTION HISTORY\n\t");
        for (Transaction transaction : accountTransactions) {
            System.out.println("\t " + transaction);
            Main.wait(15);
        }
    }
}
