package com.ficohsa.item.application.usecase;

import com.ficohsa.item.application.port.out.ItemRepository;
import com.ficohsa.item.application.port.out.TransferRepository;
import com.ficohsa.item.config.exception.CustomException;
import com.ficohsa.item.config.exception.NotFoundException;
import com.ficohsa.item.domain.constants.TypeTransfer;
import com.ficohsa.item.domain.models.Item;
import com.ficohsa.item.domain.models.Transfer;
import com.ficohsa.item.domain.service.TransferService;
import com.ficohsa.item.domain.utils.UtilFuntion;
import com.ficohsa.item.infrastructure.adapter.h2.models.ItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemUseCaseTest {
    @Mock
    ItemRepository repository;

    @InjectMocks
    ItemUseCase itemUseCase;

    @BeforeEach
    public void init(){
        this.itemUseCase=new ItemUseCase(repository);
    }

    @Test
    public void shouldGetItemsWhenSendSearch(){
        Item item= Item
                .builder()
                .id(1L)
                .name("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Mockito.when(this.repository.getItemsBySearch(Mockito.anyString(),Mockito.anyInt())).thenReturn(List.of(item));

        List<Item> items=this.itemUseCase.searchItems("Name",4);

        assertEquals(items.get(0).getId(),item.getId());
    }

    @Test
    public void shouldLaunhExecptionWhenNotFoundItems(){


        Mockito.when(this.repository.getItemsBySearch(Mockito.anyString(),Mockito.anyInt())).thenReturn(new ArrayList<>());
        assertThrows(NotFoundException.class,()->{
            this.itemUseCase.searchItems("Name",4);
        });

    }

    @Test
    public void shouldLaunhExecptionWhenSearchIsEmpty(){

        assertThrows(CustomException.class,()->{
            this.itemUseCase.searchItems("",4);
        });

    }

    @Test
    public void shouldLaunhExecptionWhenHaveMatch(){

        assertThrows(CustomException.class,()->{
            this.itemUseCase.searchItems("df{}+",4);
        });

    }

    @Test
    public void shouldGetItemById(){

        Item item= Item
                .builder()
                .id(1L)
                .name("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Mockito.when(this.repository.getItemById(Mockito.anyLong())).thenReturn(Optional.of(item));
        this.itemUseCase.getItemById(1L);


    }
    @Test
    public void shouldLaunhExpcetionWhenNotHasItem(){

        Mockito.when(this.repository.getItemById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,()->{
            this.itemUseCase.getItemById(1L);
        });



    }
}