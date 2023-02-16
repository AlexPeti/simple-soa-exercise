package gr.bank_app_soa_hashmap.service.exceptions;

public class InsufficientBalanceException extends Exception {
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException() {}

    public InsufficientBalanceException(double balance, double amount) {
        super("InsufficientBalance" + balance + "for amount " + amount);
    }
}
