package com.example.supplytracker.controller;

import com.example.supplytracker.dto.ItemDTO;
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
        ItemDTO itemDTO = new ItemDTO(1L, "Item1", "Category1", 2L, LocalDateTime.now());

        when(itemService.createItem(any(ItemDTO.class))).thenReturn(itemDTO);

        ResponseEntity<ItemDTO> response = itemController.createItem(itemDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(itemDTO, response.getBody());
        verify(itemService).createItem(itemDTO);
    }

    @Test
    void testGetAllItems() {
        ItemDTO item1 = new ItemDTO(1L, "Item1", "Category1", 2L, LocalDateTime.now());
        ItemDTO item2 = new ItemDTO(2L, "Item2", "Category2", 3L, LocalDateTime.now());
        List<ItemDTO> items = Arrays.asList(item1, item2);

        when(itemService.getAllItems()).thenReturn(items);

        ResponseEntity<List<ItemDTO>> response = itemController.getAllItems();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(itemService).getAllItems();
    }

    @Test
    void testGetItemById() {
        Long id = 1L;
        ItemDTO itemDTO = new ItemDTO(id, "Item1", "Category1", 2L, LocalDateTime.now());

        when(itemService.getItemById(id)).thenReturn(itemDTO);

        ResponseEntity<ItemDTO> response = itemController.getItemById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(itemDTO, response.getBody());
        verify(itemService).getItemById(id);
    }

    @Test
    void testUpdateItem() {
        Long id = 1L;
        ItemDTO updatedItemDTO = new ItemDTO(id, "UpdatedName", "UpdatedCategory", 2L, LocalDateTime.now());

        when(itemService.updateItem(eq(id), any(ItemDTO.class))).thenReturn(updatedItemDTO);

        ResponseEntity<ItemDTO> response = itemController.updateItem(id, updatedItemDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedItemDTO, response.getBody());
        verify(itemService).updateItem(id, updatedItemDTO);
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
