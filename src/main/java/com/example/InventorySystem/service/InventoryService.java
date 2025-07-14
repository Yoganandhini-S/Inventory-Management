package com.example.InventorySystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventorySystem.model.InventoryItem;
import com.example.InventorySystem.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public List<InventoryItem> getAllItems() {
        return repository.findAll();
    }

    public InventoryItem addItem(InventoryItem item) {
        return repository.save(item);
    }

    public InventoryItem updateItem(Long id, InventoryItem updatedItem) {
        InventoryItem item = repository.findById(id).orElseThrow();
        item.setName(updatedItem.getName());
        item.setQuantity(updatedItem.getQuantity());
        item.setCategory(updatedItem.getCategory());
        item.setPrice(updatedItem.getPrice());
        return repository.save(item);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public InventoryItem getItem(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
