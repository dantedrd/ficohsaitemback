package com.ficohsa.item.domain.service;

import com.ficohsa.item.application.usecase.AccountUseCase;
import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.domain.constants.StateAccount;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.config.exception.SPError;

import java.time.LocalDateTime;

public class TransferService {

    AccountUseCase accountUseCase;

    public TransferService(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    public Transfer transferConsignment(Transfer transfer){
        Account account=this.accountUseCase.findAccountById(transfer.getAccount().getAccountNumber());

        if(!account.getState().equals(StateAccount.ACTIVE)){
            throw new CustomException(SPError.ACCOUNT_NOT_ACTIVE.getErrorCode(), SPError.ACCOUNT_NOT_ACTIVE.getErrorMessage());
        }

        account.setAmount(account.getAmount()+transfer.getAmount());
        Account resultAccount=this.accountUseCase.updatedAccount(account);
        transfer.setAccount(resultAccount);
        transfer.setCreateAt(LocalDateTime.now());
        return transfer;
    }

    public Transfer withDrawal(Transfer transfer){
        Account account=this.accountUseCase.findAccountById(transfer.getAccount().getAccountNumber());

        if(account.getAmount()<transfer.getAmount()){
          throw new CustomException(SPError.VALUE_IS_UPPER_TO_VALUE_ACCOUNT.getErrorCode(), SPError.VALUE_IS_UPPER_TO_VALUE_ACCOUNT.getErrorMessage());
        }

        if(!account.getState().equals(StateAccount.ACTIVE)){
            throw new CustomException(SPError.ACCOUNT_NOT_ACTIVE.getErrorCode(), SPError.ACCOUNT_NOT_ACTIVE.getErrorMessage());
        }

        account.setAmount(account.getAmount()-transfer.getAmount());
        account=this.accountUseCase.updatedAccount(account);
        transfer.setAccount(account);
        transfer.setCreateAt(LocalDateTime.now());
        return transfer;
    }

    public Transfer consignament(Transfer transfer){
        Account account=this.accountUseCase.findAccountById(transfer.getAccount().getAccountNumber());
        Account accountReceptor=this.accountUseCase.findAccountById(transfer.getAccountReceptor().getAccountNumber());
        if(account.getAmount()<transfer.getAmount()){
            throw new CustomException(SPError.VALUE_IS_UPPER_TO_VALUE_ACCOUNT.getErrorCode(), SPError.VALUE_IS_UPPER_TO_VALUE_ACCOUNT.getErrorMessage());
        }

        if(!account.getState().equals(StateAccount.ACTIVE)){
            throw new CustomException(SPError.ACCOUNT_NOT_ACTIVE.getErrorCode(), SPError.ACCOUNT_NOT_ACTIVE.getErrorMessage());
        }

        account.setAmount(account.getAmount()-transfer.getAmount());
        accountReceptor.setAmount(accountReceptor.getAmount()+transfer.getAmount());
        account=this.accountUseCase.updatedAccount(account);
        accountReceptor=this.accountUseCase.updatedAccount(accountReceptor);
        transfer.setAccount(account);
        transfer.setAccountReceptor(accountReceptor);
        transfer.setCreateAt(LocalDateTime.now());
        return transfer;
    }




}
