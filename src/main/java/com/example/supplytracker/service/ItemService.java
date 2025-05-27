package com.example.supplytracker.service;

import com.example.supplytracker.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item item);
    List<Item> getAllItems();
    Item getItemById(Long id);
}