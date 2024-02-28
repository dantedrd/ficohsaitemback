package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.infrastructure.adapter.h2.models.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountDbRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    @Modifying
    @Query("delete from AccountEntity b where b.accountNumber=:accountNumber")
    void  deleteAccount(@Param("accountNumber") String accountNumber);
}
