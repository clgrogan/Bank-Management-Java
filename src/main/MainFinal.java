package src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.main.model.Bank;
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

        System.out.println();

        try {
            accounts = returnAccounts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nReturned Accounts");
        for (Account account : accounts) {
            System.out.println("\t" + account);
        }

        loadAccounts(accounts);

        System.out.println("\nBank Accounts");
        for (Account account : bigBank.accounts) {
            System.out.println("\t" + account);
        }

    }

    /**
     * Name: createObject
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

    // "Bank Management 8: Task 5"
}
