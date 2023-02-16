package gr.bank_app_soa_hashmap.dao;

import gr.bank_app_soa_hashmap.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDAOImpl implements IAccountDAO {
private static final Map<Long, Account> accounts = new HashMap<>();

    @Override
    public Account insert(Account account) {
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account update(Account account) {
        if (accounts.containsKey(account.getId())) {
            accounts.put(account.getId(), account);
        }
        return account;
    }

    @Override
    public void delete(String iban) {
        Account account = getByIban(iban);
        if (account != null) {
            accounts.remove(account.getId());
        }
    }

    @Override
    public void delete(Long id) {
        Account account = get(id);
        accounts.remove(account.getId());
    }

    @Override
    public void withdraw(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
    }

    @Override
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    @Override
    public Account get(Long id) {
        return accounts.get(id);
    }

    @Override
    public Account get(String iban) {
        return getByIban(iban);
    }

    private Account getByIban(String iban) {
        for (Account account : accounts.values()) {
            if (account.getIban().equals(iban)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public boolean ibanExists(String iban) {
        return getByIban(iban) != null;
    }

    @Override
    public boolean accountIdExists(Long id) {
        return accounts.containsKey(id);
    }
}
