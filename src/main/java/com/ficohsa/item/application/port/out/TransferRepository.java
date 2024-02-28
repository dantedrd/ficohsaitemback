package com.ficohsa.item.application.port.out;

import com.ficohsa.item.domain.models.Transfer;

public interface TransferRepository {
    Transfer saveTransfer(Transfer transfer) ;
}
