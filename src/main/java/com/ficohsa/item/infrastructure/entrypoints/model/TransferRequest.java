package com.ficohsa.item.infrastructure.entrypoints.model;

import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.domain.constants.TypeTransfer;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.config.exception.SPError;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequest {
    private Long id;
    private Double amount;
    private String typeTransfer;
    private String createAt;
    private String accountId;
    private String accountReceptorId;

    public Transfer toDomain(){
        return Transfer.builder()
                .id(this.id)
                .amount(this.amount)
                .typeTransfer(typeTransfer(this.typeTransfer))
                .account(Account.builder().accountNumber(this.accountId).build())
                .accountReceptor(Objects.nonNull(this.accountReceptorId)?
                        Account.builder().accountNumber(this.accountReceptorId).build():null)
                .build();
    }

    private TypeTransfer typeTransfer(String typeTransfer){
        try{
            return TypeTransfer.valueOf(typeTransfer);
        }catch (Exception exception){
            throw new CustomException(SPError.TYPE_TRANSFER_NOT_VALID.getErrorCode(), SPError.TYPE_TRANSFER_NOT_VALID.getErrorMessage());
        }
    }
}
