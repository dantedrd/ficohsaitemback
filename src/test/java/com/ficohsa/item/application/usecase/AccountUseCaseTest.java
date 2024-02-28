package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.AccountRepository;
import com.ficohsa.item.config.exception.NotFoundException;
import com.ficohsa.item.domain.constants.TypeAccounts;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountUseCaseTest {
    @Mock
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountUseCase accountUseCase;

    @BeforeEach
    public void init(){
        this.accountUseCase=new AccountUseCase(accountService,accountRepository);
    }

    @Test
    public void shouldGenerateExceptionAmount(){
        Account account=new Account();
        account.setAccountType(TypeAccounts.CHECKING_ACCOUNT);

        Mockito.doNothing().when(this.accountService).validate(Mockito.any(Account.class));
        Mockito.when(this.accountService.generateNumber(Mockito.any(Account.class))).thenReturn("3123234");
        Mockito.when(this.accountRepository.saveAccount(Mockito.any(Account.class))).thenReturn(account);

        Account result=this.accountUseCase.createAccount(account);

        assertNotNull(result);
    }

    @Test
    public void shouldFindAccountByNumber(){
        Account account=new Account();
        account.setAccountNumber("23423443");
        account.setAccountType(TypeAccounts.CHECKING_ACCOUNT);

        Mockito.when(this.accountRepository.findAccountById(Mockito.any(String.class))).thenReturn(Optional.of(account));

        Account result=this.accountUseCase.findAccountById("23423443");

        assertNotNull(result);
    }

    @Test
    public void shouldLaunchException(){
        Account account=new Account();
        account.setAccountNumber("23423443");
        account.setAccountType(TypeAccounts.CHECKING_ACCOUNT);

        Mockito.when(this.accountRepository.findAccountById(Mockito.any(String.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,()->{
            this.accountUseCase.findAccountById("23423443");
        });

    }

    @Test
    public void shouldUpdatedAccount(){
        Account account=new Account();
        account.setAccountType(TypeAccounts.CHECKING_ACCOUNT);

        Mockito.when(this.accountRepository.saveAccount(Mockito.any(Account.class))).thenReturn(account);

        Account result=this.accountUseCase.updatedAccount(account);

        assertNotNull(result);
    }

}