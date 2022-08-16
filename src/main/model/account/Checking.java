package src.main.model.account;

import java.math.BigDecimal;

public class Checking extends Account {

  public Checking(String id, String name, BigDecimal balance) {
    super(id, name, balance);
  }

  public Checking(Account source) {
    super(source);
  }

}
