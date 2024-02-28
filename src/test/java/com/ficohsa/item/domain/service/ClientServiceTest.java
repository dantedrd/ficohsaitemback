package com.ficohsa.item.domain.service;

import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.domain.models.Account;
import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.domain.utils.UtilFuntion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    ClientService clientService;


    @BeforeEach
    public void init(){
        this.clientService=new ClientService();
    }

    @Test
    public void shouldGenerateExceptionAge(){
        Client domain=new Client();
        domain.setBirthdate(UtilFuntion.transformStringToLocalDate("25/02/2015"));
        assertThrows(CustomException.class,()->{
            this.clientService.validateAge(domain);
        });
    }

    @Test
    public void shouldGenerateExceptionAccounts(){
        Client domain=new Client();
        domain.setBirthdate(UtilFuntion.transformStringToLocalDate("25/02/2015"));
        domain.setAccounts(List.of(new Account()));
        assertThrows(CustomException.class,()->{
            this.clientService.validateProducts(domain);
        });
    }

}