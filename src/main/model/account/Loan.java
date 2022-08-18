package src.main.model.account;

public class Loan extends Account {
  private static double WITHDRAWAL_RATE = 0.02;
  private static double MAX_DEBT = 10000.00;

  public Loan(String id, String name, double balance) {
    super(id, name, balance);
  }

  public Loan(Account source) {
    super(source);
  }

  @Override
  public void deposit(double amount) {
    this.setBalance(balance += amount);
  }

  @Override
  public void withdrawal(double amount) {
    // A withdrawal can not made if the debt exceeds $10,000.
    // Every withdrawal is charged a fixed interest rate of 2%.
    if ((getBalance() + (amount * (1 + WITHDRAWAL_RATE))) <= MAX_DEBT)
      setBalance(balance += (amount * (1 + WITHDRAWAL_RATE)));
  }

}
