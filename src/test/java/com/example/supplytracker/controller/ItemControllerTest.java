package com.example.supplytracker.controller;

import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ItemController itemController;

    // Test case for successfully creating an item when the supplier exists
     
    @Test
    void testCreateItem_Success() {
        User supplier = new User();
        supplier.setId(1L);

        Item item = new Item();
        item.setSupplier(supplier);
        item.setCreatedDate(LocalDateTime.now());

        when(userRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ResponseEntity<?> response = itemController.createItem(item);

        assertEquals(200, response.getStatusCodeValue()); // HTTP OK
        assertEquals(item, response.getBody());
        verify(itemRepository).save(item);
    }

    // Test case for failing to create an item due to an invalid supplier ID
     
    @Test
    void testCreateItem_InvalidSupplier() {
        User supplier = new User();
        supplier.setId(1L);

        Item item = new Item();
        item.setSupplier(supplier);

        when(userRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        ResponseEntity<?> response = itemController.createItem(item);

        assertEquals(400, response.getStatusCodeValue()); // when HTTP Bad Request
        assertEquals("Invalid supplier ID", response.getBody());
    }

    // Test case for retrieving all items successfully
     
    @Test
    void testGetAllItems() {
        Item item1 = new Item();
        Item item2 = new Item();
        List<Item> items = Arrays.asList(item1, item2);

        when(itemRepository.findAll()).thenReturn(items);

        List<Item> response = itemController.getAllItems();

        assertEquals(2, response.size());
        assertTrue(response.contains(item1));
        assertTrue(response.contains(item2));
    }

    // Test case for successfully retrieving an item by ID
     
    @Test
    void testGetItemById_Success() {
        Item item = new Item();
        item.setId(1L);

        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        ResponseEntity<?> response = itemController.getItemById(item.getId());

        assertEquals(200, response.getStatusCodeValue()); // wehn HTTP is OK
        assertEquals(item, response.getBody());
    }

    // Test case for handling item retrieval failure when ID does not exist.
     
    @SuppressWarnings("deprecation")
	@Test
    void testGetItemById_NotFound() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = itemController.getItemById(99L);

        assertEquals(404, response.getStatusCodeValue()); // when HTTP Not Found
    }
}