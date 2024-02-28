package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.TransferRepository;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.domain.service.TransferService;

public class TransferUseCase {
    TransferService service;
    TransferRepository repository;

    public TransferUseCase(TransferRepository repository,TransferService service) {
        this.repository=repository;
        this.service = service;
    }

    public Transfer proccess(Transfer transfer){
        Transfer transferResult=null;
        switch (transfer.getTypeTransfer()){
            case CONSIGNMENT->{
                transferResult=this.service.transferConsignment(transfer);
            }
            case WITHDRAWAL -> {
                transferResult=this.service.withDrawal(transfer);
            }
            case MOVEMENT -> {
                transferResult=this.service.consignament(transfer);
            }
        }
        return this.repository.saveTransfer(transferResult);
    }


}
