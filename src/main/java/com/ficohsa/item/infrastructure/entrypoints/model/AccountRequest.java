package com.ficohsa.item.infrastructure.entrypoints.model;

import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.domain.constants.StateAccount;
import com.ficohsa.item.domain.constants.TypeAccounts;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.models.Client;
import lombok.*;

import static com.ficohsa.item.config.exception.SPError.STATE_ACCOUNT_NOT_VALID;
import static com.ficohsa.item.config.exception.SPError.TYPE_ACCOUNT_NOT_VALID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    Long id;
    String accountType;
    String accountNumber;
    String state;
    Double amount;
    Boolean GMFExempt;
    Long clientId;

    public Account toDomain(){
        return Account.builder()
                .id(this.id)
                .accountType(getAccount(this.accountType))
                .accountNumber(this.accountNumber)
                .state(getStateAccount(this.state))
                .amount(this.amount)
                .GMFExempt(this.GMFExempt)
                .client(Client.builder().id(this.clientId).build())
                .build();
    }

    public TypeAccounts getAccount(String account){
        try{
            return TypeAccounts.valueOf(account);
        }catch (Exception exception){
            throw new CustomException(TYPE_ACCOUNT_NOT_VALID.getErrorCode(),TYPE_ACCOUNT_NOT_VALID.getErrorMessage());
        }

    }

    public StateAccount getStateAccount(String state){
        try{
            return StateAccount.valueOf(state);
        }catch (Exception exception){
            throw new CustomException(STATE_ACCOUNT_NOT_VALID.getErrorCode(),STATE_ACCOUNT_NOT_VALID.getErrorMessage());
        }
    }


}
