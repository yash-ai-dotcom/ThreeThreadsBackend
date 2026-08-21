package com.Service;

import com.Entity.Inventory;
import com.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    public Inventory createItem(Inventory item) {
        if (inventoryRepository.existsByArticleNo(item.getArticleNo())) {
            throw new IllegalArgumentException("Article Number already exists!");
        }
        return inventoryRepository.save(item);
    }

    public boolean deleteItem(Long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}