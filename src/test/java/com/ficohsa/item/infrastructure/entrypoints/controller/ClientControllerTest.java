package com.ficohsa.item.infrastructure.entrypoints.controller;

import com.ficohsa.item.application.usecase.CreateClientUseCase;
import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.infrastructure.entrypoints.model.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    @Mock
    CreateClientUseCase createClientUseCase;
    @InjectMocks
    ClientController clientController;


    @BeforeEach
    public void init(){
        this.clientController=new ClientController(createClientUseCase);
    }


    @Test
    public void shouldCreatedClient(){
        ClientRequest clientRequest=ClientRequest
                .builder()
                .typeNit("cc")
                .nit("3434")
                .email("test@gmail.com")
                .birthdate("21/03/1990")
                .build();
        Client client=new Client();

        Mockito.when(this.createClientUseCase.createClient(Mockito.any(Client.class))).thenReturn(client);

        ResponseEntity<Object> result=this.clientController.createClient(clientRequest);

        assertNotNull(result);
    }

    @Test
    public void shouldDeletedClient(){
        Mockito.doNothing().when(this.createClientUseCase).deleteClient(Mockito.anyString());

        ResponseEntity<Object> result=this.clientController.deleteClient("34435435");

        assertNotNull(result);
    }





}