package src.main.model.account;

import java.math.BigDecimal;

public class Loan extends Account {

  public Loan(String id, String name, BigDecimal balance) {
    super(id, name, balance);
  }

  public Loan(Account source) {
    super(source);
  }

}
