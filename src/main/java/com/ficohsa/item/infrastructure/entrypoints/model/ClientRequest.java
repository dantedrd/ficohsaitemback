package com.ficohsa.item.infrastructure.entrypoints.model;

import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.domain.utils.UtilFuntion;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {

    private Long id;
    private String typeNit;
    private String nit;

    @Size(min = 2, message = "El campo debe tener más de 2 caracteres")
    private String name;

    @Size(min = 2, message = "El campo debe tener más de 2 caracteres")
    private String surName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El correo electrónico no es válido")
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "El campo debe ser una fecha en formato dd/MM/yyyy")
    private String birthdate;

    public Client toDomain(){
        return Client.builder()
                .id(this.id)
                .typeNit(this.typeNit)
                .nit(this.nit)
                .name(this.name)
                .surName(this.surName)
                .email(this.email)
                .birthdate(UtilFuntion.transformStringToLocalDate(this.birthdate))
                .build();
    }

    public static ClientRequest fromDomain(Client client){
        return ClientRequest.builder()
                .id(client.getId())
                .typeNit(client.getTypeNit())
                .nit(client.getNit())
                .name(client.getName())
                .surName(client.getName())
                .email(client.getEmail())
                .birthdate(UtilFuntion.transformLocalDateToString(client.getBirthdate()))
                .build();
    }
}
