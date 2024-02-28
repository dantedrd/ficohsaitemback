package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.infrastructure.adapter.h2.models.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferDbRepository extends JpaRepository<TransferEntity, Long> {
}
