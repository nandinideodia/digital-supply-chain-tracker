package com.example.supplytracker.service;

import com.example.supplytracker.dto.ItemDTO;
import com.example.supplytracker.entity.Item;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.exception.InvalidSupplierIdException;
import com.example.supplytracker.repository.ItemRepository;
import com.example.supplytracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public ItemDTO createItem(ItemDTO itemDTO) {
        User supplier = userRepository.findById(itemDTO.getSupplierId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID"));

        Item item = dtoToEntity(itemDTO);
        item.setSupplier(supplier);
        item.setCreatedDate(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);
        return entityToDto(savedItem);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        return entityToDto(item);
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO updatedItemDTO) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));

        existingItem.setName(updatedItemDTO.getName());
        existingItem.setCategory(updatedItemDTO.getCategory());

        if (updatedItemDTO.getSupplierId() != null) {
            User supplier = userRepository.findById(updatedItemDTO.getSupplierId())
                    .orElseThrow(() -> new InvalidSupplierIdException("Invalid supplier ID"));
            existingItem.setSupplier(supplier);
        }

        Item updatedItem = itemRepository.save(existingItem);
        return entityToDto(updatedItem);
    }

    @Override
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    // Converts Item entity to ItemDTO
    private ItemDTO entityToDto(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getSupplier() != null ? item.getSupplier().getId() : null,
                item.getCreatedDate()
        );
    }
    // Converts ItemDTO to Item entity (used when creating or updating)
    private Item dtoToEntity(ItemDTO dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setCategory(dto.getCategory());
        // supplier will be set separately
        item.setCreatedDate(dto.getCreatedDate()); // optional: only if you want to preserve it
        return item;
    }
}