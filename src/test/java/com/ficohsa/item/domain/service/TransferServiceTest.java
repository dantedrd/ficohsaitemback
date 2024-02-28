package com.ficohsa.item.domain.service;

import com.ficohsa.item.application.usecase.AccountUseCase;
import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.domain.constants.StateAccount;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.models.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    AccountUseCase accountUseCase;
    @InjectMocks
    TransferService transferService;


    @BeforeEach
    public void init(){
        this.transferService=new TransferService(accountUseCase);
    }

    @Test
    public void shouldDoingTransfer(){
        Account account=new Account();
        account.setState(StateAccount.ACTIVE);
        account.setAmount(500.0);

        Transfer transfer=new Transfer();
        transfer.setAccount(Account
                .builder()
                .accountNumber("4435345")
                .amount(300.0)
                .build());
        transfer.setAmount(500.0);


        Mockito.when(this.accountUseCase.findAccountById(Mockito.anyString())).thenReturn(account);
        Mockito.when(this.accountUseCase.updatedAccount(Mockito.any(Account.class))).thenReturn(account);

        Transfer result=this.transferService.transferConsignment(transfer);

        assertEquals(result.getAccount().getAmount(),1000.0);
    }

    @Test
    public void shouldLaunhExceptionWhenAccountNotIsActive(){
        Account account=new Account();
        account.setState(StateAccount.CANCELLED);
        account.setAmount(500.0);

        Transfer transfer=new Transfer();
        transfer.setAccount(Account
                .builder()
                .accountNumber("4435345")
                .amount(300.0)
                .build());
        transfer.setAmount(500.0);


        Mockito.when(this.accountUseCase.findAccountById(Mockito.anyString())).thenReturn(account);

        assertThrows(CustomException.class,()->{
            this.transferService.transferConsignment(transfer);
        });
    }

    @Test
    public void shouldDoingWithDrawal(){
        Account account=new Account();
        account.setState(StateAccount.ACTIVE);
        account.setAmount(500.0);

        Transfer transfer=new Transfer();
        transfer.setAccount(Account
                .builder()
                .accountNumber("4435345")
                .build());
        transfer.setAmount(500.0);


        Mockito.when(this.accountUseCase.findAccountById(Mockito.anyString())).thenReturn(account);
        Mockito.when(this.accountUseCase.updatedAccount(Mockito.any(Account.class))).thenReturn(account);

        Transfer result=this.transferService.withDrawal(transfer);

        assertEquals(result.getAccount().getAmount(),0.0);
    }

    @Test
    public void shouldLaunhExceptionWhenAccountNotIsActiveInWithDrawal(){
        Account account=new Account();
        account.setState(StateAccount.CANCELLED);
        account.setAmount(500.0);

        Transfer transfer=new Transfer();
        transfer.setAccount(Account
                .builder()
                .accountNumber("4435345")
                .amount(300.0)
                .build());
        transfer.setAmount(500.0);


        Mockito.when(this.accountUseCase.findAccountById(Mockito.anyString())).thenReturn(account);

        assertThrows(CustomException.class,()->{
            this.transferService.withDrawal(transfer);
        });
    }

    @Test
    public void shouldDoingConsignament(){
        Account account=new Account();
        account.setState(StateAccount.ACTIVE);
        account.setAmount(500.0);

        Transfer transfer=new Transfer();
        transfer.setAccount(Account
                .builder()
                .accountNumber("4435345")
                .build());
        transfer.setAccountReceptor(Account
                .builder()
                .accountNumber("4435345")
                .build());
        transfer.setAmount(500.0);


        Mockito.when(this.accountUseCase.findAccountById(Mockito.anyString())).thenReturn(account);
        Mockito.when(this.accountUseCase.updatedAccount(Mockito.any(Account.class))).thenReturn(account);

        Transfer result=this.transferService.consignament(transfer);

        assertEquals(result.getAccount().getAmount(),500.0);
    }

}