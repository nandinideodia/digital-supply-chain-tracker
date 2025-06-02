package com.example.supplytracker.service;

import com.example.supplytracker.dto.ItemDTO;
import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.exception.InvalidSupplierIdException;
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
    private ItemDTO itemDTO;
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

        itemDTO = new ItemDTO(
                1L,
                "Test Item",
                "Electronics",
                supplier.getId(),
                item.getCreatedDate()
        );
    }

    @Test
    void testCreateItem_Success() {
        when(userRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ItemDTO result = itemService.createItem(itemDTO);

        assertNotNull(result);
        assertEquals("Test Item", result.getName());
        assertEquals("Electronics", result.getCategory());
        verify(userRepository).findById(supplier.getId());
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void testCreateItem_InvalidSupplier() {
        when(userRepository.findById(supplier.getId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> itemService.createItem(itemDTO));
        verify(userRepository).findById(supplier.getId());
    }

    @Test
    void testGetItemById_Success() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        ItemDTO result = itemService.getItemById(1L);

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
        Item updatedEntity = new Item();
        updatedEntity.setId(1L);
        updatedEntity.setName("Updated Item");
        updatedEntity.setCategory("Updated Category");
        updatedEntity.setSupplier(supplier);
        updatedEntity.setCreatedDate(LocalDateTime.now());

        ItemDTO updatedDTO = new ItemDTO(1L, "Updated Item", "Updated Category", 1L, updatedEntity.getCreatedDate());

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(userRepository.findById(supplier.getId())).thenReturn(Optional.of(supplier));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedEntity);

        ItemDTO result = itemService.updateItem(1L, updatedDTO);

        assertNotNull(result);
        assertEquals("Updated Item", result.getName());
        assertEquals("Updated Category", result.getCategory());
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void testUpdateItem_NotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> itemService.updateItem(1L, itemDTO));
        verify(itemRepository).findById(1L);
    }

    @Test
    void testUpdateItem_InvalidSupplier() {
        ItemDTO updatedDTO = new ItemDTO(1L, "Updated", "Updated", 999L, LocalDateTime.now());

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(InvalidSupplierIdException.class, () -> itemService.updateItem(1L, updatedDTO));
        verify(itemRepository).findById(1L);
        verify(userRepository).findById(999L);
    }

    @Test
    void testDeleteItem_Success() {
        when(itemRepository.existsById(1L)).thenReturn(true);

        itemService.deleteItem(1L);

        verify(itemRepository).existsById(1L);
        verify(itemRepository).deleteById(1L);
    }

    @Test
    void testDeleteItem_NotFound() {
        when(itemRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> itemService.deleteItem(1L));
        verify(itemRepository).existsById(1L);
    }
}
