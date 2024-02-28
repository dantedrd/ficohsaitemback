package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.infrastructure.adapter.h2.models.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ItemDbH2Repository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByNameIgnoreCaseContaining(String keyword, Pageable pageable);
}
