package gr.bank_app_soa_hashmap.service;

import gr.bank_app_soa_hashmap.dto.AccountDTO;
import gr.bank_app_soa_hashmap.model.Account;
import gr.bank_app_soa_hashmap.service.exceptions.InsufficientAmountException;
import gr.bank_app_soa_hashmap.service.exceptions.InsufficientBalanceException;
import gr.bank_app_soa_hashmap.service.exceptions.SsnNotValidException;

public interface IAccountService {
    //Public API

    /**
     * Deposits an amount of money in an {@link AccountDTO}, which is a
     * DTO that holds an {@link Account} data.
     * Throws an exception if the amount to be deposited is less than zero.
     *
     * @param accountDTO
     *  the dto of an {@link Account}.
     *
     * @param amount
     *  the amount of money to deposit.
     *
     * @return
     *  the updated {@link Account} instance.
     *
     * @throws InsufficientAmountException
     *  if the amount to insert is less than zero.
     */
   Account deposit(AccountDTO accountDTO, double amount)
        throws InsufficientAmountException;

    /**
     * Withdraws a certain amount of money from a {@link AccountDTO}, which is a dto
     * that holds an {@link Account} data. Throws an exception if the amount to be
     * withdrawn is more than the {@link AccountDTO#balance}.
     *
     * @param accountDTO
     *       the dto of an {@link Account}.
     *
     * @param amount
     *        the amount to be withdrawn.
     *
     * @return
     *        the updated {@link Account}.
     *
     * @throws InsufficientBalanceException
     *        if the amount to be withdrawn is more than the {@link AccountDTO#balance}.
     */
    Account withdraw(AccountDTO accountDTO, double amount, String ssn)
            throws InsufficientBalanceException, SsnNotValidException;

    /**
     * Gets a {@link AccountDTO#balance}.
     *
     * @param accountDTO
     *       the {@link AccountDTO}, which holds the data of an {@link Account}.
     *
     * @return
     *       the {@link AccountDTO#balance}.
     */
    double getBalance(AccountDTO accountDTO);
}
