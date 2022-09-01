package src.main.model.account;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account {
  private String id;
  private String name;
  protected BigDecimal balance;

  public Account(String id, String name, BigDecimal balance) {
    checkNullOrBlank(id);
    checkNullOrBlank(name);
    checkNull(balance);
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  private void checkNull(BigDecimal balance) {
    if (balance == null)
      throw new IllegalArgumentException("Argument may not be null.");
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

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Account)) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(id, account.id) && Objects.equals(name, account.name)
        && Objects.equals(balance, account.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, balance);
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
