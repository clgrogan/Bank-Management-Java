package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import src.main.model.Bank;
import src.main.model.Transaction;
import src.main.model.account.Checking;

// The bank keeps a record of every account created and transaction made.
// The id of a transaction matches the id of an account. 
// Depending on the account, some transactions may be denied.
// The bank can deduct taxes from taxable accounts.

/**
 * Test if the bank keeps a record an all accounts created
 * Test if the bank keeps a record of all successful transactions
 * Test if the bank ignores failed transactions
 * Test if the transaction ID matches the account ID.
 * Test if the bank denies transaction.
 * 
 */

public class BankTests {
  Bank bank;

  @Before
  public void setup() {
    bank = new Bank();
    bank.addAccount(
        new Checking("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", new BigDecimal(Double.valueOf(1524.51))));
  }

  @Test
  public void successfulTransactionTest() {
    int initTransactionsLength = bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870").length;
    this.bank.executeTransaction(new Transaction(Transaction.Type.DEPOSIT,
        1578700800, "f84c43f4-a634-4c57-a644-7602f8840870", new BigDecimal(Double.valueOf(441.93))));
    this.bank.executeTransaction(
        new Transaction(Transaction.Type.WITHDRAW, 1546905600,
            "f84c43f4-a634-4c57-a644-7602f8840870", new BigDecimal(Double.valueOf(624.99))));
    assertEquals(initTransactionsLength + 2, bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870").length);
  }

  @Test
  public void failedTransactionTest() {
    this.bank.executeTransaction(
        new Transaction(Transaction.Type.WITHDRAW, 1546905600, "f84c43f4-a634-4c57-a644-7602f8840870",
            new BigDecimal(Double.valueOf(10000000))));
    assertEquals(0, bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870").length);
  }
}