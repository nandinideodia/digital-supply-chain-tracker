package com.example.supplytracker.controller;

import com.example.supplytracker.entity.Item;
import com.example.supplytracker.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    void testCreateItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Item1");
        item.setCategory("Category1");
        item.setCreatedDate(LocalDateTime.now());

        when(itemService.createItem(any(Item.class))).thenReturn(item);

        ResponseEntity<Item> response = itemController.createItem(item);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(item, response.getBody());
        verify(itemService).createItem(item);
    }

    @Test
    void testGetAllItems() {
        Item item1 = new Item();
        Item item2 = new Item();
        List<Item> items = Arrays.asList(item1, item2);

        when(itemService.getAllItems()).thenReturn(items);

        ResponseEntity<List<Item>> response = itemController.getAllItems();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(itemService).getAllItems();
    }

    @Test
    void testGetItemById() {
        Item item = new Item();
        item.setId(1L);

        when(itemService.getItemById(1L)).thenReturn(item);

        ResponseEntity<Item> response = itemController.getItemById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(item, response.getBody());
        verify(itemService).getItemById(1L);
    }

    @Test
    void testUpdateItem() {
        Long id = 1L;
        Item updatedItem = new Item();
        updatedItem.setId(id);
        updatedItem.setName("UpdatedName");
        updatedItem.setCategory("UpdatedCategory");

        when(itemService.updateItem(eq(id), any(Item.class))).thenReturn(updatedItem);

        ResponseEntity<Item> response = itemController.updateItem(id, updatedItem);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedItem, response.getBody());
        verify(itemService).updateItem(id, updatedItem);
    }

    @Test
    void testDeleteItem() {
        Long id = 1L;
        doNothing().when(itemService).deleteItem(id);

        ResponseEntity<Void> response = itemController.deleteItem(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(itemService).deleteItem(id);
    }
}
