package co.simplon.inventory.service;

import co.simplon.inventory.client.ProductClient;
import co.simplon.inventory.dto.InventoryDtoResponse;
import co.simplon.inventory.dto.ProductDto;
import co.simplon.inventory.entity.Inventory;
import co.simplon.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public Inventory createInventory(UUID productId, int initialQuantity) {
        Inventory inventory = new Inventory(null, productId, initialQuantity);
        return inventoryRepository.save(inventory);
    }

    public Optional<Inventory> getStockByProductId(UUID productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public void updateStock(UUID productId, int newQuantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for Product ID: " + productId));

        inventory.setStockQuantity(newQuantity);

        inventoryRepository.save(inventory);
    }

    public Optional<InventoryDtoResponse> getInventoryDetails(UUID productId) {
        return inventoryRepository.findByProductId(productId)
                .map(inventory -> {
                    ProductDto product = productClient.getProduct(productId);
                    return new InventoryDtoResponse(
                            product.name(),
                            product.category(),
                            inventory.getStockQuantity());
                });
    }
}
