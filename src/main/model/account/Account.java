package src.main.model.account;

import java.math.BigDecimal;

public abstract class Account {
  private String id;
  private String name;
  private BigDecimal balance;

  public Account(String id, String name, BigDecimal balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public Account(Account source) {
    this.id = source.id;
    this.name = source.name;
    this.balance = source.balance;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

}
