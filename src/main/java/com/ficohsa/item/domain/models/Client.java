package com.ficohsa.item.domain.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    Long id;
    String typeNit;
    String nit;
    String name;
    String surName;
    String email;
    LocalDate birthdate;
    LocalDateTime createAt;
    LocalDateTime changeDate;
    private List<Account> accounts;
}
