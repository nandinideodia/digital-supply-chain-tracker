package com.example.supplytracker.service;

import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.UserRepository;
import com.example.supplytracker.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

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
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + id));
    }
}