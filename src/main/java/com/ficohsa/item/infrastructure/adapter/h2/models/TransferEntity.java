package com.ficohsa.item.infrastructure.adapter.h2.models;


import com.ficohsa.item.domain.constants.TypeTransfer;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.domain.utils.UtilFuntion;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transfer")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String typeTransfer;
    private String createAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_receptor_id", referencedColumnName = "id")
    private AccountEntity accountReceptor;


    public static TransferEntity fromDomain(Transfer transfer) {
        return TransferEntity.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .typeTransfer(transfer.getTypeTransfer().getTypeTransfer())
                .createAt(UtilFuntion.transformLocalDateTimeToString(transfer.getCreateAt()))
                .account(AccountEntity.fromDomain(transfer.getAccount()))
                .accountReceptor(Objects.nonNull(transfer.getAccountReceptor())?AccountEntity.fromDomain(transfer.getAccountReceptor()):null)
                .build();
    }

    public  Transfer toDomain() {
        return Transfer.builder()
                .id(this.id)
                .amount(this.amount)
                .typeTransfer(TypeTransfer.valueOf(this.typeTransfer))
                .createAt(UtilFuntion.transformStringToLocalDateTime(this.createAt))
                .account(this.account.toDomain())
                .accountReceptor(Objects.nonNull(this.accountReceptor)?this.accountReceptor.toDomain():null)
                .build();
    }


}
