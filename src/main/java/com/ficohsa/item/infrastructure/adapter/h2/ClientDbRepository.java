package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.infrastructure.adapter.h2.models.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDbRepository extends JpaRepository<ClientsEntity, Long> {


    Optional<ClientsEntity> findByNit(String nit);

    @Modifying
    @Query("delete from ClientsEntity b where b.nit=:nit")
    void  deleteClient(@Param("nit") String nit);
}
