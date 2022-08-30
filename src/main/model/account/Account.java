package src.main.model.account;

import java.math.BigDecimal;

public abstract class Account {
  private String id;
  private String name;
  protected BigDecimal balance;

  public Account(String id, String name, BigDecimal balance) {
    checkNullOrBlank(id);
    checkNullOrBlank(name);
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public Account(Account source) {
    if (source == null)
      throw new IllegalArgumentException("Argument may not be null.");
    checkNullOrBlank(source.id);
    checkNullOrBlank(source.name);
    this.id = source.id;
    this.name = source.name;
    this.balance = source.balance;
  }

  // Abstract Methods
  public abstract void deposit(BigDecimal amount);

  public abstract void withdrawal(BigDecimal amount);

  public abstract Account clone();

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    checkNullOrBlank(id);
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    checkNullOrBlank(name);
    this.name = name;
  }

  public BigDecimal getBalance() {
    return new BigDecimal(this.balance.toString());
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void checkNullOrBlank(String o) throws IllegalArgumentException {
    String violation = "NULL";
    if (o == null || o.isBlank()) {
      if (o != null)
        violation = "EMPTY";
      throw new IllegalArgumentException("\n " + violation + " Argument. May not be null or blank.");
    }
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "     "
        + "\t" + id + ""
        + "\t" + name + ""
        + "\t$" + balance + "";
  }
}
