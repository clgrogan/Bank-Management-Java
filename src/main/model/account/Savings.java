package src.main.model.account;

import java.math.BigDecimal;

public class Savings extends Account {

  public Savings(String id, String name, BigDecimal balance) {
    super(id, name, balance);
  }

  public Savings(Savings source) {
    super(source);
  }

}
