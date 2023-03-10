package gr.bank_app_soa_hashmap.service.exceptions;

public class SsnNotValidException extends Exception {
    private static final long serialVersionUID = 1L;

    public SsnNotValidException() {}

    public SsnNotValidException(String ssn) {
        super("SSN " + ssn + " is not valid.");
    }
}
