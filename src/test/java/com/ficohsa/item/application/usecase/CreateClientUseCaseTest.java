package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.ClientRepository;
import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.domain.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateClientUseCaseTest {

    @Mock
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    CreateClientUseCase createClientUseCase;

    @BeforeEach
    public void init(){
        this.createClientUseCase=new CreateClientUseCase(clientRepository,clientService);
    }

    @Test
    public void shouldGenerateClient(){
        Client client=new Client();
        client.setName("test");

        Mockito.doNothing().when(this.clientService).validateAge(Mockito.any(Client.class));
        Mockito.when(this.clientRepository.saveClient(Mockito.any(Client.class))).thenReturn(client);

        Client result=this.createClientUseCase.createClient(client);

        assertNotNull(result);
    }

    @Test
    public void shouldLaunchExeptionWhenClientHaveAccounts(){
        Mockito.when(this.clientRepository.findClientAndTransferByNit(Mockito.anyString())).thenReturn(Optional.empty());

        assertThrows(CustomException.class,()->{
            this.createClientUseCase.deleteClient("32434");
        });
    }

    @Test
    public void shouldDeleteClient(){
        Client client=new Client();
        client.setName("test");

        Mockito.doNothing().when(this.clientService).validateProducts(Mockito.any(Client.class));
        Mockito.doNothing().when(this.clientRepository).deleteClient(Mockito.anyString());

        Mockito.when(this.clientRepository.findClientAndTransferByNit(Mockito.anyString())).thenReturn(Optional.of(client));

        this.createClientUseCase.deleteClient("3434");

        verify(this.clientService).validateProducts(Mockito.any(Client.class));
        verify(this.clientRepository).deleteClient(Mockito.anyString());
    }

    @Test
    public void shouldLaunchExeptionWhenDeleteClient(){
        Client client=new Client();
        client.setName("test");

        Mockito.when(this.clientRepository.findClientAndTransferByNit(Mockito.anyString())).thenThrow(new CustomException(433,"4334"));


        assertThrows(CustomException.class,()->{
            this.createClientUseCase.deleteClient("3434");
        });



    }
}