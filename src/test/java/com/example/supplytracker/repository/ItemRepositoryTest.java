package com.example.supplytracker.repository;

import com.example.supplytracker.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testSaveItem() {
        Item item = new Item();
        item.setName("Test Item");

        Item savedItem = itemRepository.save(item);

        assertThat(savedItem).isNotNull();
        assertThat(savedItem.getId()).isNotNull();
        assertThat(savedItem.getName()).isEqualTo("Test Item");
    }

    @Test
    public void testFindById() {
        Item item = new Item();
        item.setName("Another Item");
        Item savedItem = itemRepository.save(item);

        Item retrievedItem = itemRepository.findById(savedItem.getId()).orElse(null);

        assertThat(retrievedItem).isNotNull();
        assertThat(retrievedItem.getName()).isEqualTo("Another Item");
    }
}