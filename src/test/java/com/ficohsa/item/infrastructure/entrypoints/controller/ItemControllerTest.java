package com.ficohsa.item.infrastructure.entrypoints.controller;

import com.ficohsa.item.application.usecase.ItemUseCase;
import com.ficohsa.item.domain.models.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemUseCase itemUseCase;

    @InjectMocks
    private ItemController itemController;

    @Test
    public void testHelloEndpoint(){

        Item item= Item
                .builder()
                .id(1L)
                .name("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Mockito.when(this.itemUseCase.searchItems(Mockito.anyString(),Mockito.anyInt())).thenReturn(List.of(item));


        ResponseEntity<Object> items=this.itemController.searchItems("324234",2);
        assertNotNull(items.getBody());
    }

    @Test
    public void shoudSearchItems(){

        Item item= Item
                .builder()
                .id(1L)
                .name("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Mockito.when(this.itemUseCase.searchItems(Mockito.anyString(),Mockito.anyInt())).thenReturn(List.of(item));


        ResponseEntity<Object> items=this.itemController.searchItems("324234",2);
        assertNotNull(items.getBody());
    }

    @Test
    public void shoudFindByItemId(){

        Item item= Item
                .builder()
                .id(1L)
                .name("test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Mockito.when(this.itemUseCase.getItemById(Mockito.anyLong())).thenReturn(item);


        ResponseEntity<Object> items=this.itemController.getItem(324234L);
        assertNotNull(items.getBody());
    }

}