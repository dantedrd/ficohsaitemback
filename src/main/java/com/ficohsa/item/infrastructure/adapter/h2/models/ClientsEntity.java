package com.ficohsa.item.infrastructure.adapter.h2.models;

import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.domain.utils.UtilFuntion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class ClientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_nit")
    private String typeNit;
    private String nit;
    private String name;
    @Column(name="surname")
    private String surName;
    private String email;
    private String birthdate;
    @Column(name="created_at")
    private String createAt;
    @Column(name="change_date")
    private String changeDate;

    @OneToMany(mappedBy = "client")
    private List<AccountEntity> accounts;


    public static ClientsEntity fromDomain(Client domain) {
        return ClientsEntity.builder()
                .id(domain.getId())
                .typeNit(domain.getTypeNit())
                .nit(domain.getNit())
                .name(domain.getName())
                .surName(domain.getSurName())
                .email(domain.getEmail())
                .birthdate(UtilFuntion.transformLocalDateToString(domain.getBirthdate()))
                .createAt(UtilFuntion.transformLocalDateTimeToString(domain.getCreateAt()))
                .changeDate(UtilFuntion.transformLocalDateTimeToString(domain.getChangeDate()))
                .build();
    }

    public Client toDomain() {
        return Client.builder()
                .id(this.id)
                .typeNit(this.typeNit)
                .nit(this.nit)
                .name(this.name)
                .surName(this.surName)
                .email(this.email)
                .birthdate(UtilFuntion.transformStringToLocalDate(this.getBirthdate()))
                .createAt(UtilFuntion.transformStringToLocalDateTime(this.createAt))
                .changeDate(UtilFuntion.transformStringToLocalDateTime(this.changeDate))
                .build();
    }



}
