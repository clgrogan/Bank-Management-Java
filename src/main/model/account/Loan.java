package src.main.model.account;

import java.math.BigDecimal;

public class Loan extends Account {
  private static final BigDecimal WITHDRAWAL_RATE = new BigDecimal(Double.valueOf(0.02));
  private static final BigDecimal WITHDRAWAL_RATE_MULTIPLIER = new BigDecimal(Double.valueOf(1.02));
  private static final BigDecimal MAX_DEBT = new BigDecimal(Double.valueOf(10000));

  public Loan(String id, String name, BigDecimal balance) {
    super(id, name, balance);
  }

  public Loan(Account source) {
    super(source);
  }

  @Override
  public Account clone() {
    return new Loan(this);
  }

  @Override
  public void deposit(BigDecimal amount) {

    BigDecimal initialGetBalance = this.getBalance();
    BigDecimal initialBalance = this.balance;
    BigDecimal depositAmount = new BigDecimal(Double.valueOf("187.22"));
    // System.out.println("Initial Balance: " + initialBalance);
    System.out.println("Amount of Trans: " + amount);
    // System.out.println("Expected Balance: " + expectedBalance);
    System.out.println("balance pre subtract: " + this.balance);
    // this.setBalance(this.balance.subtract(amount));
    System.out.println("balance post subtract: " + this.balance);
    this.setBalance(this.balance.subtract(amount));
  }

  @Override
  public void withdrawal(BigDecimal amount) {
    BigDecimal newBalance = this.balance.add(amount.multiply(WITHDRAWAL_RATE_MULTIPLIER));
    if (newBalance.compareTo(MAX_DEBT) <= 0)
      setBalance(newBalance);
  }

}
