package src.main.model.account;

import src.main.model.account.impl.Taxable;

public class Checking extends Account implements Taxable {

  private static final double TAX_RATE = 0.02;
  private static final double TAX_THRESHOLD = 3000;
  private static final double OVERDRAFT_FEE = 5.50;
  private static final double OVERDRAFT_LIMIT = 200.00;

  public Checking(String id, String name, double balance) {
    super(id, name, balance);
  }

  public Checking(Account source) {
    super(source);
  }

  @Override
  public void deposit(double amount) {
    double depositAmount = amount;
    if (amount > TAX_THRESHOLD) {
      depositAmount -= (amount - TAX_THRESHOLD) * TAX_RATE;
    }
    this.setBalance(this.balance += depositAmount);

  }

  @Override
  public void withdrawal(double amount) {
    // The checking account charges an overdraft fee of $5.50 if the amount being
    // withdrawn exceeds the account balance.

    if (this.balance < amount)
      amount += OVERDRAFT_FEE;

    if (this.balance - amount >= -OVERDRAFT_LIMIT)
      this.setBalance(this.balance -= amount);

  }

}
