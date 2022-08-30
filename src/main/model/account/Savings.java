package src.main.model.account;

import java.math.BigDecimal;

public class Savings extends Account {

  public static final BigDecimal WITHDRAWAL_FEE = new BigDecimal(Double.valueOf(5.00));
  public static final BigDecimal MIN_BALANCE = new BigDecimal(Double.valueOf(0));

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
    BigDecimal netWithdrawal = amount.add(WITHDRAWAL_FEE);
    if (this.balance.subtract(netWithdrawal).compareTo(MIN_BALANCE) >= 0)
      this.setBalance(balance.subtract(netWithdrawal));
  }

}
