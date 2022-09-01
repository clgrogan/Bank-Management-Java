package src.main.model.account;

import java.math.BigDecimal;

public abstract class Constants {

  public static final BigDecimal TAX_RATE = new BigDecimal(Double.valueOf(0.15));
  public static final BigDecimal TAX_THRESHOLD = new BigDecimal(Double.valueOf(3000));
  public static final BigDecimal OVERDRAFT_FEE = new BigDecimal(Double.valueOf(5.50));
  public static final BigDecimal MIN_BALANCE_CHECKING = new BigDecimal(Double.valueOf(-200.00));
  public static final BigDecimal WITHDRAWAL_FEE = new BigDecimal(Double.valueOf(5.00));
  public static final BigDecimal MIN_BALANCE_SAVINGS = new BigDecimal(Double.valueOf(0));

}
