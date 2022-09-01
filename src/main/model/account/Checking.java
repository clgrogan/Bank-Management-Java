package src.main.model.account;

import java.math.BigDecimal;
import src.main.model.account.interfaces.Taxable;
import src.main.utils.Constants;

public class Checking extends Account implements Taxable {

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
      amount = amount.add(Constants.OVERDRAFT_FEE);
    if (this.balance.subtract(amount).compareTo(Constants.MINIMUM_BALANCE) >= 0)
      this.setBalance(this.balance.subtract(amount));

  }

  @Override
  public void tax(BigDecimal amount) {
    if (amount.compareTo(Constants.TAX_THRESHOLD) > 0) {
      BigDecimal taxes = amount.subtract(Constants.TAX_THRESHOLD).multiply(Constants.TAX_RATE);
      this.setBalance(balance.subtract(taxes));
    }
  }
}
