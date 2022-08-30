package src.main.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

import src.main.model.account.Account;

public class Bank {
  public ArrayList<Account> accounts;
  private ArrayList<Transaction> transactions;

  public Bank() {
    accounts = new ArrayList<Account>();
    transactions = new ArrayList<Transaction>();
  }

  /**
   * Name: addAccount
   * 
   * @param account (Account)
   * 
   *                Inside the function:
   *                1. adds an account to the accounts ArrayList
   */
  public void addAccount(Account account) {
    accounts.add(account.clone());
  }

  /**
   * Name: addTransaction
   * 
   * @param transaction
   *                    1. adds a new transaction object to the array list.
   */
  private void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  /**
   * Name: getTransactions
   * 
   * @param accoundId (String)
   * @return (Transaction[])
   * 
   *         1. returns an array of transactions whose id matches the accountId
   */
  public Transaction[] getTransactions(String accountId) {
    return transactions.stream()
        .filter((element -> element.getId().equals(accountId)))
        .collect(Collectors.toList())
        .toArray(Transaction[]::new);
  }

  public Transaction[] getTransactions() {
    return transactions.stream()
        .collect(Collectors.toList())
        .toArray(Transaction[]::new);

  }

  /**
   * Name: getAccount()
   * 
   * @param transactionId (String)
   * @return (Account)
   * 
   *         1. returns an account whose id matches a transaction.
   */
  public Account getAccount(String id) {
    return accounts.stream()
        .filter(e -> e.getId().equals(id))
        .findFirst().orElse(null);
  }

  /**
   * Name: executeTransaction
   * 
   * @param transaction
   * 
   *                    Inside the function:
   *                    1. calls withdrawTransaction if transaction type is
   *                    WITHDRAW
   *                    2. calls depositTransaction if transaction type is DEPOSIT
   * 
   */
  public void executeTransaction(Transaction transaction) {
    Account account = this.getAccount(transaction.id);
    BigDecimal initialBalance = account.getBalance();

    if (transaction.getType().toString() == "DEPOSIT")
      account.deposit(transaction.amount);
    else
      account.withdrawal(transaction.amount);

    if (initialBalance.compareTo(account.getBalance()) != 0)
      this.addTransaction(transaction);
  }

  public void deductTaxes() {

  }
}
