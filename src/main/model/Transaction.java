package src.main.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Comparable<Transaction> {
  public enum Type {
    WITHDRAW, DEPOSIT
  };

  protected Type type;
  protected long timestamp;
  protected String id;
  protected BigDecimal amount;

  public Transaction(Type type, long timestamp, String id, BigDecimal amount) {
    checkNullOrBlank(id);
    checkNullOrNegative(amount);
    this.type = type;
    this.timestamp = timestamp;
    this.id = id;
    this.amount = amount;
  }

  public Transaction(Transaction source) {
    checkNullOrBlank(source.id);
    checkNullOrNegative(source.amount);
    this.type = source.type;
    this.timestamp = source.timestamp;
    this.id = source.id;
    this.amount = source.amount;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    checkNullOrBlank(id);
    this.id = id;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    checkNullOrNegative(amount);
    this.amount = amount;
  }

  public String returnDate() {
    Date dateTimestamp = new Date(this.timestamp * 1000);
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
    return simpleDateFormat1.format(dateTimestamp);
  }

  public void checkNullOrBlank(String o) throws IllegalArgumentException {
    String violation = "NULL";
    if (o == null || o.isBlank()) {
      if (o != null)
        violation = "EMPTY";
      throw new IllegalArgumentException("\n " + violation + " Argument. May not be null or blank.");
    }
  }

  public void checkNullOrNegative(BigDecimal amount) {
    if (amount == null)
      throw new IllegalArgumentException("The 'amount' argument can't be null.");
    if (amount.signum() == -1)
      throw new IllegalArgumentException("The 'amount' argument can't be negative.");
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Transaction)) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(type, transaction.type) && timestamp == transaction.timestamp
        && Objects.equals(id, transaction.id) && amount == transaction.amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, timestamp, id, amount);
  }

  @Override
  public String toString() {
    return (type) + "    " +
        "\t" + returnDate() + "" +
        "\t" + id + "" +
        "\t$" + amount + "";
  }

  @Override
  public int compareTo(Transaction specifiedTransaction) {
    return Long.compare(this.getTimestamp(), specifiedTransaction.getTimestamp());
  }
}
