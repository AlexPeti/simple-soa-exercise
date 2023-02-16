package gr.bank_app_soa_hashmap.model;

public class Account extends AbstractEntity {
    private String iban;
    private double balance;
    private Holder holder;

    public Account() {}

    public Account(String iban, double balance, Holder holder) {
        this.iban = iban;
        this.balance = balance;
        this.holder = holder;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                ", holder=" + holder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.getBalance(), getBalance()) != 0) return false;
        if (!getIban().equals(account.getIban())) return false;
        return getHolder().equals(account.getHolder());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getIban().hashCode();
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getHolder().hashCode();
        return result;
    }
}
