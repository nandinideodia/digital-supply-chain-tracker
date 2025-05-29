package com.example.supplytracker.service;

import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock private ItemRepository itemRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private ItemServiceImpl itemService;

    private Item item;
    private User supplier;

    @BeforeEach
    void setUp() {
        supplier = new User();
        supplier.setId(1L);

        item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        item.setCategory("Electronics");
        item.setSupplier(supplier);
        item.setCreatedDate(LocalDateTime.now());
    }

    @Test
    void testCreateItem_Success() {
        when(userRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        Item result = itemService.createItem(item);

        assertNotNull(result);
        assertEquals("Test Item", result.getName());
        verify(itemRepository).save(any(Item.class));
        verify(userRepository).findById(supplier.getId());
    }

    @Test
    void testCreateItem_InvalidSupplier() {
        when(userRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> itemService.createItem(item));
        verify(userRepository).findById(supplier.getId());
    }

    @Test
    void testGetItemById_Success() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Item result = itemService.getItemById(1L);

        assertNotNull(result);
        assertEquals("Test Item", result.getName());
        verify(itemRepository).findById(1L);
    }

    @Test
    void testGetItemById_NotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> itemService.getItemById(1L));
        verify(itemRepository).findById(1L);
    }

    @Test
    void testUpdateItem_Success() {
        Item updatedItem = new Item();
        updatedItem.setName("Updated Item");
        updatedItem.setCategory("Updated Category");
        updatedItem.setSupplier(supplier);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(userRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        Item result = itemService.updateItem(1L, updatedItem);

        assertNotNull(result);
        assertEquals("Updated Item", result.getName());
        assertEquals("Updated Category", result.getCategory());
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void testUpdateItem_NotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> itemService.updateItem(1L, item));
        verify(itemRepository).findById(1L);
    }

    @Test
    void testDeleteItem_Success() {
        when(itemRepository.existsById(1L)).thenReturn(true);

        itemService.deleteItem(1L);

        verify(itemRepository).deleteById(1L);
        verify(itemRepository).existsById(1L);
    }

    @Test
    void testDeleteItem_NotFound() {
        when(itemRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> itemService.deleteItem(1L));
        verify(itemRepository).existsById(1L);
    }
}