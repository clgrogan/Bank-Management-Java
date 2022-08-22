package src.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.model.account.Account;
import src.main.model.account.Checking;
import src.main.model.account.Loan;
import src.main.model.account.Savings;
import src.main.model.account.interfaces.Taxable;;;

public class AccountTests {

  Account[] accounts;

  @Before
  public void setup() {
    accounts = new Account[] { new Checking("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51),
        new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60),
        new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", 2537.31) };
  }

  // Test if deposit works (all).
  @Test
  public void depositLessThan3000CheckingTest() {

    double initialBalance = accounts[0].getBalance();
    double amount = 187.22;
    accounts[0].deposit(amount);
    assertTrue((initialBalance + amount) == accounts[0].getBalance());
  }

  // Test if deposit amounts in excess of $3000.00 are taxed at 15% (checking)
  @Test
  public void taxIncomeCheckingTest() {
    double taxRate = 0.15;
    double initialBalance = accounts[0].getBalance();
    double amount = 10000;
    double taxes = (10000 - 3000) * taxRate;
    accounts[0].deposit(amount);
    ((Taxable) accounts[0]).tax(amount);
    assertTrue((initialBalance + amount - taxes) == accounts[0].getBalance());
  }

  // Test if deposit works (all).
  @Test
  public void depositSavingsTest() {
    double initialBalance = accounts[1].getBalance();
    double amount = 187.22;
    accounts[1].deposit(amount);
    assertTrue((initialBalance + amount) == accounts[1].getBalance());
  }

  // Test if deposit works (all).
  @Test
  public void depositLoanTest() {

    double initialBalance = accounts[2].getBalance();
    double amount = 187.22;
    accounts[2].deposit(amount);
    assertTrue((initialBalance + amount) == accounts[2].getBalance());
  }

  // Test if withdrawal charges $5 fee (savings)
  @Test
  public void withdrawalSavingsTest() {
    double withdrawalFee = 5.00;
    double initialBalance = accounts[1].getBalance();
    double amount = 187.22;
    accounts[1].withdrawal(amount);
    assertTrue((initialBalance - amount - withdrawalFee) == accounts[1].getBalance());
  }

  // Test if withdrawal prevents negative balance (savings)
  @Test
  public void withdrawalSavingsNegativeBalanceTest() {
    double initialBalance = accounts[1].getBalance();
    accounts[1].withdrawal(initialBalance);
    assertTrue(initialBalance == accounts[1].getBalance());
  }

  // Test if withdrawal charges a fee of 2% (loan)
  @Test
  public void withdrawalLoanUnderMaxTest() {
    double withdrawalRate = 0.02;
    double initialBalance = accounts[2].getBalance();
    double amount = 1000;
    accounts[2].withdrawal(amount);
    assertTrue((initialBalance += amount * (1 + withdrawalRate)) == accounts[2].getBalance());
  }

  // Test if withdrawal prevents debit balance > $10,000. (loan)
  @Test
  public void withdrawalLoanOverMaxTest() {
    double initialBalance = accounts[2].getBalance();
    double amount = 10000;
    accounts[2].withdrawal(amount);
    assertTrue(initialBalance == accounts[2].getBalance());
  }

  // Test withdrawal
  @Test
  public void withdrawalCheckingTest() {
    double initialBalance = accounts[0].getBalance();
    double amount = 200.00;
    accounts[0].withdrawal(amount);
    assertTrue(initialBalance - amount == accounts[0].getBalance());
  }

  // Test if overdraft fee is applied for withdrawals that enter negative balance.
  // (checking)
  @Test
  public void withdrawalCheckingOverdraftTest() {
    double initialBalance = accounts[0].getBalance();
    double amount = initialBalance + 50;
    accounts[0].withdrawal(amount);
    assertTrue(initialBalance - amount - 5.50 == accounts[0].getBalance());
  }

  // Test if overdraft is limited to $200 (checking)
  @Test
  public void withdrawalCheckingOverdraftLimitExceededTest() {
    double initialBalance = accounts[0].getBalance();
    double amount = initialBalance + 200;
    accounts[0].withdrawal(amount);
    assertTrue(initialBalance == accounts[0].getBalance());
  }

  @Test
  public void cloneAccountTest() {

    Account clonedAccount = accounts[0].clone();
    assertNotEquals(accounts[0], clonedAccount);
  }

}
