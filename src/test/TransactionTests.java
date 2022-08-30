package src.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import src.main.model.Transaction;

public class TransactionTests {
  Transaction transaction;

  @Before
  public void setup() {
    transaction = new Transaction(Transaction.Type.WITHDRAW, 1546905600, "6b8dd258-aba3-4b19-b238-45d15edd4b48",
        new BigDecimal(Double.valueOf(624.99)));
  }

  @Test
  public void correctDateTest() {
    assertEquals("07-01-2019", transaction.returnDate());
  }

  @Test
  public void testIdIsNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      Transaction transaction2 = new Transaction(Transaction.Type.WITHDRAW, 1546905600, null,
          new BigDecimal(Double.valueOf(624.99)));
    });
  }

  @Test
  public void testIdIsBlank() {
    assertThrows(IllegalArgumentException.class, () -> {
      Transaction transaction2 = new Transaction(Transaction.Type.WITHDRAW, 1546905600, "",
          new BigDecimal(Double.valueOf(624.99)));
    });
  }

  @Test
  public void testAmountIsNegative() {
    assertThrows(IllegalArgumentException.class, () -> {
      Transaction transaction2 = new Transaction(Transaction.Type.WITHDRAW, 1546905600,
          "6b8dd258-aba3-4b19-b238-45d15edd4b48",
          new BigDecimal(Double.valueOf(-1.00)));
    });
  }
}
