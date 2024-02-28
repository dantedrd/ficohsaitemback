package com.ficohsa.item.infrastructure.adapter.h2;

import com.ficohsa.item.domain.models.Item;
import com.ficohsa.item.domain.utils.UtilFuntion;
import com.ficohsa.item.infrastructure.adapter.h2.models.ItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemDbH2RepositoryAdapterTest {
    @Mock
    ItemDbH2Repository itemDbH2Repository;
    @InjectMocks
    ItemDbH2RepositoryAdapter itemDbH2RepositoryAdapter;


    @BeforeEach
    public void init(){
        this.itemDbH2RepositoryAdapter=new ItemDbH2RepositoryAdapter(itemDbH2Repository);
    }

    @Test
    public void shouldGetItemBySearch(){
        ItemEntity itemEntity= ItemEntity
                .builder()
                .id(1L)
                .name("test")
                .createdAt(UtilFuntion.transformLocalDateTimeToDate(LocalDateTime.now()))
                .updatedAt(UtilFuntion.transformLocalDateTimeToDate(LocalDateTime.now()))
                .build();


        Mockito.when(this.itemDbH2Repository.findByNameIgnoreCaseContaining(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(List.of(itemEntity));

        List<Item> result=this.itemDbH2RepositoryAdapter.getItemsBySearch("Name",4);

        assertEquals(result.get(0).getId(),itemEntity.getId());
    }

    @Test
    public void shouldGetItemWhenSendId(){
        ItemEntity itemEntity= ItemEntity
                .builder()
                .id(1L)
                .name("test")
                .createdAt(UtilFuntion.transformLocalDateTimeToDate(LocalDateTime.now()))
                .updatedAt(UtilFuntion.transformLocalDateTimeToDate(LocalDateTime.now()))
                .build();


        Mockito.when(this.itemDbH2Repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(itemEntity));

        Optional<Item> result=this.itemDbH2RepositoryAdapter.getItemById(4L);

        assertEquals(result.get().getId(),itemEntity.getId());
    }
}