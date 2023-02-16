package gr.bank_app_soa_hashmap.main;

import gr.bank_app_soa_hashmap.dao.AccountDAOImpl;
import gr.bank_app_soa_hashmap.dao.IAccountDAO;
import gr.bank_app_soa_hashmap.dto.AccountDTO;
import gr.bank_app_soa_hashmap.dto.HolderDTO;
import gr.bank_app_soa_hashmap.service.AccountServiceImpl;
import gr.bank_app_soa_hashmap.service.IAccountService;
import gr.bank_app_soa_hashmap.service.exceptions.InsufficientAmountException;
import gr.bank_app_soa_hashmap.service.exceptions.InsufficientBalanceException;
import gr.bank_app_soa_hashmap.service.exceptions.SsnNotValidException;

public class Main {

    public static void main(String[] args) throws InsufficientBalanceException, SsnNotValidException, InsufficientAmountException {
        // Creating first account DTO instance.
        AccountDTO accountDTO = new AccountDTO();
        HolderDTO holderDTO = new HolderDTO();
        holderDTO.setFirstname("Alice");
        holderDTO.setLastname("W.");
        holderDTO.setHolderID(1L);
        holderDTO.setSsn("12345");
        accountDTO.setHolderDTO(holderDTO);
        accountDTO.setAccountId(1L);
        accountDTO.setIban("1234");
        accountDTO.setBalance(5000);

        // Creating a new DAO instance.
        IAccountDAO accountDAO = new AccountDAOImpl();
        // Creating a services instance.
        IAccountService accountService = new AccountServiceImpl(accountDAO);
        // Checking if the withdrawal and deposit methods work correctly and printing the results.
        accountService.withdraw(accountDTO,3000,accountDTO.getHolderDTO().getSsn());
        System.out.println("Starting balance: 5000, Withdrawing: 3000, Should be: 2000, Result: " + accountService.getBalance(accountDTO));

        accountService.withdraw(accountDTO,500,accountDTO.getHolderDTO().getSsn());
        System.out.println("Balance: 2000, Withdrawing: 500, Should be: 1500, Result: " + accountService.getBalance(accountDTO));

        accountService.withdraw(accountDTO,400,accountDTO.getHolderDTO().getSsn());
        System.out.println("Balance: 1500, Withdrawing: 400, Should be: 1100, Result: " + accountService.getBalance(accountDTO));

        accountService.deposit(accountDTO,2000);
        System.out.println("Balance 1100, Depositing: 2000, Should be: 3100, Result: " + accountService.getBalance(accountDTO));

        // Creating a second account DTO instance.
        AccountDTO newAccountDTO = new AccountDTO();
        HolderDTO newHolderDTO = new HolderDTO();
        newHolderDTO.setFirstname("Bob");
        newHolderDTO.setLastname("Dylan");
        newHolderDTO.setHolderID(2L);
        newHolderDTO.setSsn("54321");
        newAccountDTO.setHolderDTO(newHolderDTO);
        newAccountDTO.setAccountId(2L);
        newAccountDTO.setIban("4321");
        newAccountDTO.setBalance(7000);

        // Testing the second account using our service instance.
        accountService.deposit(newAccountDTO,2000);
        System.out.println("Account 2 Balance: 7000, Deposit: 2000, Should be: 9000, Result: " + accountService.getBalance(newAccountDTO));
        accountService.withdraw(newAccountDTO,3000,newAccountDTO.getHolderDTO().getSsn());
        System.out.println("Account 2 Balance: 9000, Withdrawing: 3000, Should be: 6000, Result: " + accountService.getBalance(newAccountDTO));

        // Testing if both accounts are stored correctly in the database. We should have 2 account instances
        //and after testing, the first one should have a balance of 3100 and the second one a balance of 6000.
        System.out.println(accountDAO.getAll());
    }
}
