package com.example.supplytracker.service;

import com.example.supplytracker.dto.ItemDTO;
import com.example.supplytracker.entity.Item;

import java.util.List;

public interface ItemService {

    // Create a new item from ItemDTO and return the created ItemDTO
    ItemDTO createItem(ItemDTO itemDTO);

    // Get all items as a list of ItemDTOs
    List<ItemDTO> getAllItems();

    // Get a single item by ID and return as ItemDTO
    ItemDTO getItemById(Long id);

    // Update an item by ID using ItemDTO and return updated ItemDTO
    ItemDTO updateItem(Long id, ItemDTO itemDTO);

    // Delete an item by ID
    void deleteItem(Long id);
}