package com.example.supplytracker.controller;

import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    // POST /api/items
    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        Optional<User> supplier = userRepository.findById(item.getSupplier().getId());

        if (!supplier.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid supplier ID");
        }

        item.setSupplier(supplier.get());
        item.setCreatedDate(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }

    // GET /api/items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // GET /api/items/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);

        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(item.get());
    }
}