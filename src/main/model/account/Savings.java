package src.main.model.account;

import java.math.BigDecimal;

public class Savings extends Account {

  public Savings(String id, String name, BigDecimal balance) {
    super(id, name, balance);
  }

  public Savings(Savings source) {
    super(source);
  }

  @Override
  public Account clone() {
    return new Savings(this);
  }

  @Override
  public void deposit(BigDecimal amount) {
    this.setBalance(balance.add(amount));
  }

  @Override
  public void withdrawal(BigDecimal amount) {
    BigDecimal netWithdrawal = amount.add(Constants.WITHDRAWAL_FEE);
    if (this.balance.subtract(netWithdrawal).compareTo(Constants.MIN_BALANCE) >= 0)
      this.setBalance(balance.subtract(netWithdrawal));
  }

}
