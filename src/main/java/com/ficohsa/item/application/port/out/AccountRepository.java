package com.ficohsa.item.application.port.out;

import com.ficohsa.item.domain.models.Account;

import java.util.Optional;

public interface AccountRepository {
    Account saveAccount(Account account);

    Optional<Account> findAccountById(String accountNumber);

    void deleteAccount(String accountNumber);
}
