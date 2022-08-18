package src.main.model.account;

public abstract class Account {
  private String id;
  private String name;
  protected double balance;

  public Account(String id, String name, double balance) {
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

  public abstract void deposit(double amount);

  public abstract void withdrawal(double amount);

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

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
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
