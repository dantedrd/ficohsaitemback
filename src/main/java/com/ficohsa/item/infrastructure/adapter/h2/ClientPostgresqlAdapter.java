package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.application.port.out.ClientRepository;
import com.ficohsa.item.domain.models.Client;
import com.ficohsa.item.infrastructure.adapter.h2.models.AccountEntity;
import com.ficohsa.item.infrastructure.adapter.h2.models.ClientsEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientPostgresqlAdapter implements ClientRepository {

    private static final Logger logger = LoggerFactory.getLogger(ClientPostgresqlAdapter.class);
    private final ClientDbRepository clientDbRepository;


    /**
     * Constructor for dependency injection of the ClientDbRepository.
     * @param clientDbRepository The Employer repository interface for crud.
     */
    public ClientPostgresqlAdapter(ClientDbRepository clientDbRepository) {
        this.clientDbRepository = clientDbRepository;
    }

    @Override
    public Optional<Client> findClientAndTransferByNit(String nit) {
        return this.clientDbRepository.findByNit(nit)
                .map(clientsEntity -> {
                   Client client=clientsEntity.toDomain();
                   client.setAccounts(clientsEntity
                           .getAccounts()
                           .stream()
                           .map(AccountEntity::toDomain)
                           .toList());
                   return client;
                });
    }

    @Override
    public Client saveClient(Client employee) {
        return this.clientDbRepository.save(ClientsEntity.fromDomain(employee)).toDomain();
    }

    @Override
    @Transactional
    public void deleteClient(String nit) {
        clientDbRepository.deleteClient(nit);
    }
}
