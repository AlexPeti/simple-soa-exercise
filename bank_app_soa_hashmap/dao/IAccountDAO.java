package gr.bank_app_soa_hashmap.dao;

import gr.bank_app_soa_hashmap.model.Account;

import java.util.List;

public interface IAccountDAO {

    /**
     * Inserts a new {@link Account} in the datasource.
     *
     * @param account
     *       the model object that contains the account data.
     *
     * @return
     *       the resulting {@link Account}
     */
    Account insert(Account account);

    /**
     * Updates a new {@link Account} instance in the datasource.
     *
     * @param account
     *        the model object that contains the account data.
     *
     * @return
     *        the model object instance.
     */
    Account update(Account account);

    /**
     * Removes a {@link Account} from the datasource.
     *
     * @param iban
     *        the {@link Account#iban} of the instance
     *        needed to be removed.
     */
    void delete(String iban);

    /**
     * Removes a {@link Account} from the datasource.
     *
     * @param id
     *        the {@link Account#id} of the instance
     *        needed to be removed.
     */
    void delete(Long id);

    /**
     * Withdraws the given amount from the specified {@link Account}.
     *
     * @param account the account to withdraw from
     * @param amount the amount to withdraw
     */
    void withdraw(Account account, double amount);

    /**
     * Deposits the given amount into the specified {@link Account}.
     *
     * @param account the account to deposit into
     * @param amount the amount to deposit
     */
    void deposit(Account account, double amount);

    /**
     * Returns a {@link Account} based on the input id.
     *
     * @param id
     *       the {@link Account#id} of the instance to be returned.
     *
     * @return
     *       the resulting {@link Account}.
     */
    Account get(Long id);

    /**
     * Returns a {@link Account} based on the input iban.
     *
     * @param iban
     *       the {@link Account#iban} of the instance to be returned.
     *
     * @return
     *       the resulting {@link Account}.
     */
    Account get(String iban);

    /**
     * Returns all {@link Account} instances from the datasource.
     *
     * @return
     *       the resulting {@link Account} instances.
     */
    List<Account> getAll();

    /**
     * Checks if an iban already exists as part of a {@link Account}.
     *
     * @param iban
     *        the {@link Account#iban} to be checked.
     *
     * @return
     *        true, if the iban exists as part of a
     *        {@link Account}.
     */
    boolean ibanExists(String iban);

    /**
     * Checks if an id already exists as part of a {@link Account}.
     *
     * @param id
     *        the {@link Account#id} to be checked.
     *
     * @return
     *       true, if the id exists as part of a
     *       {@link Account}.
     */
    boolean accountIdExists(Long id);

}
