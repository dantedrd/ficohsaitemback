package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.TransferRepository;
import com.ficohsa.item.domain.constants.TypeTransfer;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.domain.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransferUseCaseTest {
    @Mock
    TransferService service;

    @Mock
    TransferRepository repository;
    @InjectMocks
    TransferUseCase transferUseCase;

    @BeforeEach
    public void init(){
        this.transferUseCase=new TransferUseCase(repository,service);
    }

    @Test
    public void shouldGenerateTransferConsignment(){
        Transfer transfer=new Transfer();
        transfer.setTypeTransfer(TypeTransfer.CONSIGNMENT);

        Mockito.when(this.service.transferConsignment(Mockito.any(Transfer.class))).thenReturn(transfer);
        Mockito.when(this.repository.saveTransfer(Mockito.any(Transfer.class))).thenReturn(transfer);

        Transfer result=this.transferUseCase.proccess(transfer);

        assertNotNull(result);
    }

    @Test
    public void shouldGenerateTransferMovement(){
        Transfer transfer=new Transfer();
        transfer.setTypeTransfer(TypeTransfer.MOVEMENT);

        Mockito.when(this.service.consignament(Mockito.any(Transfer.class))).thenReturn(transfer);
        Mockito.when(this.repository.saveTransfer(Mockito.any(Transfer.class))).thenReturn(transfer);

        Transfer result=this.transferUseCase.proccess(transfer);

        assertNotNull(result);
    }

    @Test
    public void shouldGenerateTransferWithDrawal(){
        Transfer transfer=new Transfer();
        transfer.setTypeTransfer(TypeTransfer.WITHDRAWAL);

        Mockito.when(this.service.withDrawal(Mockito.any(Transfer.class))).thenReturn(transfer);
        Mockito.when(this.repository.saveTransfer(Mockito.any(Transfer.class))).thenReturn(transfer);

        Transfer result=this.transferUseCase.proccess(transfer);

        assertNotNull(result);
    }

}