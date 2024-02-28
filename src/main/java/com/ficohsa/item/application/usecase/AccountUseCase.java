package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.AccountRepository;
import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.config.exception.NotFoundException;
import com.ficohsa.item.config.exception.SPError;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.service.AccountService;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ficohsa.item.config.exception.SPError.NOT_FOUND_ACCOUNT;

public class AccountUseCase {
    AccountService accountService;
    AccountRepository accountRepository;

    /**
     * Constructs a new instance of {@code AccountUseCase} with the given storage repository and service
     * @param accountRepository The storage repository used for persisting accounts.
     * @param accountService The util client service is responsible for managing all client validation operations.
     */
    public AccountUseCase(AccountService accountService,AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository=accountRepository;
    }

    public Account createAccount(Account account){
        this.accountService.validate(account);
        account.setAccountNumber(this.accountService.generateNumber(account));
        account.setCreateAt(LocalDateTime.now());
        account.setChangeDate(LocalDateTime.now());
        return this.accountRepository.saveAccount(account);
    }

    public Account findAccountById(String number){
        Optional<Account> account=this.accountRepository.findAccountById(number);
        if(!account.isPresent()){
            throw new NotFoundException(NOT_FOUND_ACCOUNT.getErrorCode(),NOT_FOUND_ACCOUNT.getErrorMessage());
        }
        return account.get();
    }

    public Account updatedAccount(Account account){
        account.setChangeDate(LocalDateTime.now());
        return this.accountRepository.saveAccount(account);
    }

    public void deleteAccount(String accountNumber){
        try {
            this.accountRepository.deleteAccount(accountNumber);
        }catch (Exception ex){
            throw new CustomException(SPError.ERROR_DELETE_ACCOUNT.getErrorCode(),SPError.ERROR_DELETE_ACCOUNT.getErrorMessage());
        }
    }
}
