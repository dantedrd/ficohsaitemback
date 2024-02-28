package com.ficohsa.item.domain.service;

import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.config.exception.SPError;
import com.ficohsa.item.domain.models.Client;

import java.time.LocalDate;
import java.time.Period;

public class ClientService {

    public void validateAge(Client domain) {
        int age = Period.between(domain.getBirthdate(), LocalDate.now()).getYears();
        if(age<18){
            throw new CustomException(SPError.INVALID_AGE_CLIENT.getErrorCode(),SPError.INVALID_AGE_CLIENT.getErrorMessage());
        }
    }

    public void validateProducts(Client domain) {
        if(domain.getAccounts().size()>0){
            throw new CustomException(SPError.INVALID_CLIENT_HAS_PRODUCTS.getErrorCode(),SPError.INVALID_CLIENT_HAS_PRODUCTS.getErrorMessage());
        }
    }

}
