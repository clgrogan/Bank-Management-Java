package src.main.model.account;

public class Savings extends Account {

  public static final double WITHDRAWAL_FEE = 5.00;

  public Savings(String id, String name, double balance) {
    super(id, name, balance);
  }

  public Savings(Savings source) {
    super(source);
  }

  @Override
  public void deposit(double amount) {
    this.setBalance(balance += amount);
  }

  @Override
  public void withdrawal(double amount) {
    if ((balance - amount - WITHDRAWAL_FEE) >= 0)
      this.setBalance(balance -= amount + WITHDRAWAL_FEE);
  }

}
