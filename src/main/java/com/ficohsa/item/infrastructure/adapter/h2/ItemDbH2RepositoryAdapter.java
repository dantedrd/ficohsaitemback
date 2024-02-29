package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.application.port.out.ItemRepository;
import com.ficohsa.item.domain.models.Item;
import com.ficohsa.item.infrastructure.adapter.h2.models.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class ItemDbH2RepositoryAdapter implements ItemRepository {

    private static final Logger logger = LoggerFactory.getLogger(ItemDbH2RepositoryAdapter.class);

    private final ItemDbH2Repository itemDbH2Repository;


    /**
     * Constructor for dependency injection of the ClientDbRepository.
     * @param itemDbH2Repository The repository interface for managing items in the database.
     */
    public ItemDbH2RepositoryAdapter(ItemDbH2Repository itemDbH2Repository) {
        this.itemDbH2Repository=itemDbH2Repository;
    }

    /**
     * Retrieves a paginated list of items whose names contain a specified search string,
     * ignoring case sensitivity.
     *
     * @param search The search string to match against item names.
     * @param total  The total number of items to retrieve.
     * @return A paginated list of items whose names contain the specified search string.
     */
    @Override
    public List<Item> getItemsBySearch(String search,Integer total) {
        Pageable pageable = PageRequest.of(0, total);
        return this.itemDbH2Repository.findByNameIgnoreCaseContaining(search,pageable).
                stream()
                .map(ItemEntity::toDomain)
                .toList();
    }

    /**
     * Searches the database for an item by its unique identifier.
     *
     * @param id The unique identifier of the item to search for.
     * @return An Optional object containing the found item, or an empty Optional if no item with the specified identifier is found.
     */
    @Override
    public Optional<Item> getItemById(Long id) {
        return this.itemDbH2Repository.findById(id)
                .map(ItemEntity::toDomain);
    }

    @Override
    public Item saveItem(Item item) {
        return this.itemDbH2Repository.save(ItemEntity.fromDomain(item)).toDomain();
    }
}
