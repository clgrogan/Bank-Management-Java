package src.main.model.account;

import java.math.BigDecimal;
import src.main.model.account.interfaces.Taxable;

public class Checking extends Account implements Taxable, Constants {

  public Checking(String id, String name, BigDecimal balance) {
    super(id, name, balance);
  }

  public Checking(Account source) {
    super(source);
  }

  @Override
  public Account clone() {
    return new Checking(this);
  }

  @Override
  public void deposit(BigDecimal amount) {
    this.setBalance(balance.add(amount));

  }

  @Override
  public void withdrawal(BigDecimal amount) {
    if (this.balance.compareTo(amount) < 0)
      amount = amount.add(OVERDRAFT_FEE);
    if (this.balance.subtract(amount).compareTo(MIN_BALANCE_CHECKING) >= 0)
      this.setBalance(this.balance.subtract(amount));

  }

  @Override
  public void tax(BigDecimal amount) {
    if (amount.compareTo(TAX_THRESHOLD) > 0) {
      BigDecimal taxes = amount.subtract(TAX_THRESHOLD).multiply(TAX_RATE);
      this.setBalance(balance.subtract(taxes));
    }
  }
}
