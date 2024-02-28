package com.ficohsa.item.application.port.out;

import com.ficohsa.item.domain.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item>
    getItemsBySearch(String search,Integer total);
    Optional<Item> getItemById(Long id);
}
