package co.simplon.inventory.controller;

import co.simplon.inventory.dto.InventoryDtoResponse;
import co.simplon.inventory.entity.Inventory;
import co.simplon.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{productId}")
    public Inventory initializeInventory(@PathVariable UUID productId,
                                         @RequestParam(defaultValue = "0") int initialQuantity) {
        Inventory inventory = inventoryService.createInventory(productId, initialQuantity);

        logger.info("Inventory created for Product ID: " + productId + " with quantity: " + initialQuantity);

        return inventory;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryDtoResponse> getStock(@PathVariable UUID productId) {
        return inventoryService.getInventoryDetails(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateStock(@PathVariable UUID productId,
                                              @RequestParam int newQuantity) {
        // Appelle le service pour mettre à jour le stock
        inventoryService.updateStock(productId, newQuantity);

        // Retourne une réponse indiquant que la mise à jour a réussi
        return ResponseEntity.ok("Stock updated for Product ID: " + productId + " with new quantity: " + newQuantity);
    }

}
