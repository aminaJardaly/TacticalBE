package com.Tactical.TacticalBE.service;

import com.Tactical.TacticalBE.dto.ItemRequestDto;
import com.Tactical.TacticalBE.exception.ItemNotFoundException;
import com.Tactical.TacticalBE.model.Item;
import com.Tactical.TacticalBE.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(ItemRequestDto request) {
        Item item = new Item(null, request.getName(), request.getDescription());
        return itemRepository.save(item);
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(String id, ItemRequestDto request) {
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));
        existingItem.setName(request.getName());
        existingItem.setDescription(request.getDescription());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(String id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item not found with ID: " + id);
        }
        itemRepository.deleteById(id);
    }
}
