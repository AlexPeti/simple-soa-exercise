package gr.bank_app_soa_hashmap.service;

import gr.bank_app_soa_hashmap.dao.IAccountDAO;
import gr.bank_app_soa_hashmap.dto.AccountDTO;
import gr.bank_app_soa_hashmap.dto.HolderDTO;
import gr.bank_app_soa_hashmap.model.Account;
import gr.bank_app_soa_hashmap.model.Holder;
import gr.bank_app_soa_hashmap.service.exceptions.InsufficientAmountException;
import gr.bank_app_soa_hashmap.service.exceptions.InsufficientBalanceException;
import gr.bank_app_soa_hashmap.service.exceptions.SsnNotValidException;

public class AccountServiceImpl implements IAccountService {
    private final IAccountDAO dao;

    public AccountServiceImpl(IAccountDAO dao) {
        this.dao = dao;
    }

    /**
     * Maps an {@link AccountDTO} to an {@link Account}.
     *
     * @param account
     *        the {@link Account} instance.
     *
     * @param accountDTO
     *        the {@link AccountDTO}, which holds the {@link Account} data.
     */

    private void mapAccount(Account account, AccountDTO accountDTO) {
        account.setId(accountDTO.getAccountId());
        account.setBalance(accountDTO.getBalance());
        account.setIban(accountDTO.getIban());
        Holder holder = new Holder();
        mapHolder(holder, accountDTO.getHolderDTO());
        account.setHolder(holder);
    }

    /**
     * Maps a {@link HolderDTO} to a {@link Holder}.
     *
     * @param holder
     *        the {@link Holder} instance.
     *
     * @param holderDTO
     *        the {@link HolderDTO} that holds the {@link Holder} data.
     */
    private void mapHolder(Holder holder, HolderDTO holderDTO) {
        holder.setId(holderDTO.getHolderID());
        holder.setFirstname(holderDTO.getFirstname());
        holder.setLastname(holderDTO.getLastname());
        holder.setSsn(holderDTO.getSsn());
    }

    /**
     * Deposits an amount of money in an {@link AccountDTO}, which is a
     * DTO that holds an {@link Account} data.
     * Throws an exception if the amount to be deposited is less than zero.
     *
     * @param accountDTO
     *        the dto of an {@link Account}.
     *
     * @param amount
     *        the amount of money to deposit.
     *
     * @return
     *        the updated {@link Account} instance.
     *
     * @throws InsufficientAmountException
     *        if the amount to insert is less than zero.
     */
    @Override
    public Account deposit(AccountDTO accountDTO, double amount) throws InsufficientAmountException {
        // Retrieve the account from the database using the IBAN
        Account storedAccount = dao.get(accountDTO.getIban());
        // If the account doesn't exist, create a new instance and map it from the accountDTO
        if(storedAccount == null) {
            storedAccount = new Account();
            mapAccount(storedAccount,accountDTO);
            dao.insert(storedAccount);
        }
        // Check if the amount is negative, if so throw an exception
        if(amount < 0) {
            throw new InsufficientAmountException();
        } else {
            // Deposit the amount into the account
            dao.deposit(storedAccount, amount);
            // Update the account information in the database
            dao.update(storedAccount);
        }
        // Return the updated account
        return storedAccount;
    }

    /**
     * Withdraws a certain amount of money from a {@link AccountDTO}, which is a dto
     * that holds an {@link Account} data. Throws an exception if the amount to be
     * withdrawn is more than the {@link AccountDTO#balance}.
     *
     * @param accountDTO the dto of an {@link Account}.
     * @param amount     the amount to be withdrawn.
     * @param ssn        the {@link HolderDTO} ssn.
     * @return           the
     * @throws InsufficientBalanceException if the amount to be withdrawn is more than the {@link AccountDTO#balance}.
     * @throws SsnNotValidException if the {@link HolderDTO#ssn is invalid.
     */
    @Override
    public Account withdraw(AccountDTO accountDTO, double amount, String ssn)
            throws InsufficientBalanceException, SsnNotValidException {
        // Retrieve the account from the database using the IBAN
        Account storedAccount = dao.get(accountDTO.getIban());
        // If the account doesn't exist, create a new instance and map it from the accountDTO
        if(storedAccount == null) {
            storedAccount = new Account();
            mapAccount(storedAccount, accountDTO);
            dao.insert(storedAccount);
        }
        // Check if the ssn provided is not equal to the ssn of the account holder, if so throw an exception
        if (!storedAccount.getHolder().getSsn().equals(ssn)) {
            throw new SsnNotValidException();
        }
        // Check if the amount is greater than the account balance, if so throw an exception
        if (amount > storedAccount.getBalance()) {
            throw new InsufficientBalanceException();
        }
        // Withdraw the amount from the account
        dao.withdraw(storedAccount, amount);
        // Update the account information in the database
        dao.update(storedAccount);
        // Return the updated account
        return storedAccount;
    }

    /**
     * Gets a {@link AccountDTO#balance}.
     *
     * @param accountDTO
     *       the {@link AccountDTO}, which holds the data of an {@link Account}.
     *
     * @return
     *       the {@link AccountDTO#balance}.
     */
    @Override
    public double getBalance(AccountDTO accountDTO) {
        Account account = dao.get(accountDTO.getIban());
        return account.getBalance();
    }

    private boolean isSsnValid(AccountDTO accountDTO, String ssn) {
        Account account = dao.get(accountDTO.getAccountId());
        mapAccount(account,accountDTO);
        if (accountDTO.getHolderDTO() == null || accountDTO.getHolderDTO().getSsn() == null) {
            return false;
        }
        return ssn.equals(accountDTO.getHolderDTO().getSsn());
    }

}
