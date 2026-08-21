package com.Controller;

import com.Entity.Inventory;
import com.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*") // Allows Render static domain requests without CORS blocks
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Inventory item) {
        try {
            Inventory savedItem = inventoryService.createItem(item);
            return ResponseEntity.ok(savedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        boolean deleted = inventoryService.deleteItem(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}