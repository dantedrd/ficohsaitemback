package com.ficohsa.item.application.port.out;

import com.ficohsa.item.domain.models.Client;

import java.util.Optional;

public interface ClientRepository {

    Optional<Client> findClientAndTransferByNit(String nit);
    Client saveClient(Client employee);

    void deleteClient(String nit);
}
