package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import src.main.model.account.Account;
import src.main.model.account.Checking;
import src.main.model.account.Loan;
import src.main.model.account.Savings;
import src.main.model.account.interfaces.Taxable;
import src.main.utils.Constants;

public class AccountTests {

  Account[] accounts;

  @Before
  public void setup() {
    accounts = new Account[] {
        new Checking("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", new BigDecimal(Double.valueOf(1524.51))),
        new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", new BigDecimal(Double.valueOf(2241.60))),
        new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", new BigDecimal(Double.valueOf(2537.31)))
    };
  }

  // Test if deposit works (all).
  @Test
  public void depositLessThan3000CheckingTest() {
    BigDecimal initialBalance = accounts[0].getBalance();
    BigDecimal amount = new BigDecimal(Double.valueOf(187.22));
    BigDecimal expectedBalance = initialBalance.add(amount);
    accounts[0].deposit(amount);
    assertTrue(expectedBalance.compareTo(accounts[0].getBalance()) == 0);
  }

  // Test if deposit amounts in excess of $3000.00 are taxed at 15% (checking)
  @Test
  public void taxIncomeCheckingTest() {
    BigDecimal initialBalance = accounts[0].getBalance();
    BigDecimal amount = new BigDecimal(Double.valueOf(10000));
    BigDecimal taxes = amount.subtract(Constants.TAX_THRESHOLD).multiply(Constants.TAX_RATE);
    accounts[0].deposit(amount);
    ((Taxable) accounts[0]).tax(amount);
    assertTrue(initialBalance.add(amount).subtract(taxes).compareTo(accounts[0].getBalance()) == 0);
  }

  // Test if deposit works (all).
  @Test
  public void depositSavingsTest() {
    BigDecimal initialBalance = accounts[1].getBalance();
    BigDecimal amount = new BigDecimal(Double.valueOf(187.22));
    BigDecimal expectedBalance = initialBalance.add(amount);
    accounts[1].deposit(amount);
    assertTrue(expectedBalance.compareTo(accounts[1].getBalance()) == 0);
  }

  // Test if deposit works (all).
  @Test
  public void depositLoanTest() {
    BigDecimal amount = new BigDecimal(Double.valueOf(200));
    BigDecimal compBalance = accounts[2].getBalance().subtract(amount);
    accounts[2].deposit(amount);
    assertTrue(compBalance.compareTo(accounts[2].getBalance()) == 0);
  }

  // Test if withdrawal charges $5 fee (savings)
  @Test
  public void withdrawalSavingsTest() {
    BigDecimal currentBalance = accounts[1].getBalance();
    BigDecimal amount = new BigDecimal(Double.valueOf(187.22));
    currentBalance = currentBalance.subtract(amount).subtract(Constants.WITHDRAWAL_FEE);
    accounts[1].withdrawal(amount);
    assertTrue((currentBalance.compareTo(accounts[1].getBalance()) == 0));
  }

  // Test if withdrawal prevents negative balance (savings)
  @Test
  public void withdrawalSavingsNegativeBalanceTest() {
    BigDecimal initialBalance = accounts[1].getBalance();
    accounts[1].withdrawal(initialBalance);
    assertTrue(initialBalance.compareTo(accounts[1].getBalance()) == 0);
  }

  // Test if withdrawal charges a fee of 2% (loan)
  @Test
  public void withdrawalLoanUnderMaxTest() {
    BigDecimal withdrawalRateMultiplier = new BigDecimal(Double.valueOf(1.02));
    BigDecimal compBalance = accounts[2].getBalance();
    BigDecimal amount = new BigDecimal(Double.valueOf(1000));
    accounts[2].withdrawal(amount);
    compBalance = compBalance.add(amount.multiply(withdrawalRateMultiplier));
    assertTrue((compBalance.compareTo(accounts[2].getBalance())) == 0);
  }

  // Test if withdrawal prevents debit balance > $10,000. (loan)
  @Test
  public void withdrawalLoanOverMaxTest() {
    BigDecimal initialBalance = accounts[2].getBalance();
    BigDecimal amount = new BigDecimal(Double.valueOf(10000));
    accounts[2].withdrawal(amount);
    assertTrue(initialBalance.compareTo(accounts[2].getBalance()) == 0);
  }

  // Test withdrawal
  @Test
  public void withdrawalCheckingTest() {
    BigDecimal amount = new BigDecimal(Double.valueOf(200.00));
    BigDecimal compBalance = accounts[0].getBalance().subtract(amount);
    accounts[0].withdrawal(amount);
    assertTrue(compBalance.compareTo(accounts[0].getBalance()) == 0);
  }

  // Test if overdraft fee is applied for withdrawals that enter negative balance.
  // (checking)
  @Test
  public void withdrawalCheckingOverdraftTest() {
    BigDecimal amount = new BigDecimal(Double.valueOf(50.00)).add(accounts[0].getBalance());
    BigDecimal compBalance = accounts[0].getBalance().subtract(amount).subtract(Constants.OVERDRAFT_FEE);
    accounts[0].withdrawal(amount);
    assertTrue(compBalance.compareTo(accounts[0].getBalance()) == 0);
  }

  // Test if overdraft is limited to $200 (checking)
  @Test
  public void withdrawalCheckingOverdraftLimitExceededTest() {
    BigDecimal amount = Constants.MINIMUM_BALANCE.abs().add(accounts[0].getBalance());
    BigDecimal compBalance = accounts[0].getBalance();
    accounts[0].withdrawal(amount);
    assertTrue(compBalance.compareTo(accounts[0].getBalance()) == 0);
  }

  @Test
  public void cloneAccountTest() {

    Account clonedAccount = accounts[0].clone();
    assertEquals(accounts[0], clonedAccount);
    assertFalse(accounts[0] == clonedAccount);
  }

  @Test
  public void testNullOrEmptyExceptionTest() {
    assertThrows(IllegalArgumentException.class, () -> {
      Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      Loan loan = new Loan(null, "Phoebe Buffay",
          new BigDecimal(Double.valueOf(666.66)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      Loan loan = new Loan("", "Phoebe Buffay",
          new BigDecimal(Double.valueOf(666.66)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", null,
          new BigDecimal(Double.valueOf(666.66)));
    });

    assertThrows(IllegalArgumentException.class, () -> {
      Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "",
          new BigDecimal(Double.valueOf(666.66)));
    });
  }

}
