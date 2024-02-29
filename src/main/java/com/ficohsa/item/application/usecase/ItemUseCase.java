package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.ItemRepository;
import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.config.exception.NotFoundException;
import com.ficohsa.item.domain.models.Item;
import java.util.regex.Pattern;
import java.util.List;
import static com.ficohsa.item.config.exception.SPError.*;

public class ItemUseCase {
    ItemRepository repository;

    private final Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]+");

    public ItemUseCase(ItemRepository repository) {
        this.repository=repository;
    }

    /**
     * Searches for items in the database based on a specified search string,
     * ignoring case sensitivity, and retrieves a paginated list of matching items.
     *
     * @param search The search string to match against item names.
     * @param total  The total number of items to retrieve.
     * @return A paginated list of items whose names contain the specified search string.
     * @throws CustomException If the search string is empty or not valid according to the specified pattern.
     * @throws NotFoundException If no items matching the search criteria are found in the database.
     */
    public List<Item> searchItems(String search,Integer total){
        if(search.isEmpty()){
          throw new CustomException(PARAM_SEARCH_NOT_EMPTY.getErrorCode(),PARAM_SEARCH_NOT_EMPTY.getErrorMessage());
        }
        if (!pattern.matcher(search).matches()) {
            throw new CustomException(PARAM_SEARCH_NOT_VALID.getErrorCode(),PARAM_SEARCH_NOT_VALID.getErrorMessage());
        }
       List<Item> items= this.repository.getItemsBySearch(search,total);
       if(items.isEmpty()){
           throw new NotFoundException(ITEMS_NOT_FOUND.getErrorCode(),ITEMS_NOT_FOUND.getErrorMessage());
       }
       return items;
    }

    public Item getItemById(Long id){
        return this.repository.getItemById(id)
                .orElseThrow(() -> new NotFoundException(ITEM_NOT_FOUND.getErrorCode(),ITEM_NOT_FOUND.getErrorMessage()));
    }

    public Item saveItem(Item item){
        return this.repository.saveItem(item);
    }

}
