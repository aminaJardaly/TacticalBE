package com.Tactical.TacticalBE.service;

import com.Tactical.TacticalBE.dto.ItemRequestDto;
import com.Tactical.TacticalBE.dto.ItemResponseDto;
import com.Tactical.TacticalBE.exception.ItemNotFoundException;
import com.Tactical.TacticalBE.model.Item;
import com.Tactical.TacticalBE.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponseDto createItem(ItemRequestDto request) {
        Item item = new Item(request.getName(), request.getDescription());
        return new ItemResponseDto(itemRepository.save(item));
    }

    public ItemResponseDto getItemById(String id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));
        return new ItemResponseDto(item);
    }

    public List<ItemResponseDto> getAllItems() {
        return itemRepository.findAll().stream().map(ItemResponseDto::new).collect(Collectors.toList());
    }

    public ItemResponseDto updateItem(String id, ItemRequestDto request) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        return new ItemResponseDto(itemRepository.save(item));
    }

    public void deleteItem(String id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item not found with ID: " + id);
        }
        itemRepository.deleteById(id);
    }
}

