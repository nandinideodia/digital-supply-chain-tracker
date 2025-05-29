package com.example.supplytracker.service;

import com.example.supplytracker.exception.InvalidSupplierIdException;


import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.UserRepository;
import com.example.supplytracker.service.ItemService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Item createItem(Item item) {
        User supplier = userRepository.findById(item.getSupplier().getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID"));
        item.setSupplier(supplier);
        item.setCreatedDate(LocalDateTime.now());
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Override
    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));

        existingItem.setName(updatedItem.getName());
        existingItem.setCategory(updatedItem.getCategory());

        if (updatedItem.getSupplier() != null) {
            userRepository.findById(updatedItem.getSupplier().getId())
                    .orElseThrow(() -> new InvalidSupplierIdException("Invalid supplier ID"));
            existingItem.setSupplier(updatedItem.getSupplier());
        }

        return itemRepository.save(existingItem);
    }

    @Override
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
       
}