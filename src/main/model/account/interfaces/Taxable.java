package src.main.model.account.interfaces;

import java.math.BigDecimal;

public interface Taxable {
  public void tax(BigDecimal income);
}
