package com.Tactical.TacticalBE.controller;

import com.Tactical.TacticalBE.dto.ItemRequestDto;
import com.Tactical.TacticalBE.dto.ItemResponseDto;
import com.Tactical.TacticalBE.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@Validated
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@Valid @RequestBody ItemRequestDto request) {
        return ResponseEntity.ok(new ItemResponseDto(itemService.createItem(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(new ItemResponseDto(itemService.getItemById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        List<ItemResponseDto> responseDtos = itemService.getAllItems()
                .stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable String id, @Valid @RequestBody ItemRequestDto request) {
        return ResponseEntity.ok(new ItemResponseDto(itemService.updateItem(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
