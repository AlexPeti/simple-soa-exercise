package gr.bank_app_soa_hashmap.dto;

public class AccountDTO {
    private long accountId;
    private HolderDTO holderDTO;
    private String iban;
    private double balance;

    public AccountDTO() {}

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public HolderDTO getHolderDTO() {
        return holderDTO;
    }

    public void setHolderDTO(HolderDTO holderDTO) {
        this.holderDTO = holderDTO;
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
}
