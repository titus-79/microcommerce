package co.simplon.product.service;

import co.simplon.product.client.InventoryClient;
import co.simplon.product.entity.Product;
import co.simplon.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public ProductService(ProductRepository productRepository, InventoryClient inventoryClient) {
        this.productRepository = productRepository;
        this.inventoryClient = inventoryClient;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        Product savedProduct = productRepository.save(product);
        inventoryClient.initializeStock(savedProduct.getId());
        return savedProduct;
    }
}
