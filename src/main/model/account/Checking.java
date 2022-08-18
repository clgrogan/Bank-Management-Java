package src.main.model.account;

import src.main.model.account.interfaces.Taxable;

public class Checking extends Account implements Taxable {

  private static final double TAX_RATE = 0.15;
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
  public Account clone() {
    return new Checking(this);
  }

  public void test(double amount) {
    System.out.println("Method: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    System.out.println("\tParameter(s):\n\t\tamount: $" + amount);
  }

  @Override
  public void deposit(double amount) {
    this.setBalance(this.balance += amount);

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

  @Override
  public void tax(double amount) {
    if (amount > TAX_THRESHOLD) {
      this.setBalance(balance -= (amount - TAX_THRESHOLD) * TAX_RATE);
    }
  }
}
