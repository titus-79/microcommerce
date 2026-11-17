package co.simplon.inventory.client;

import co.simplon.inventory.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class ProductClient {
    private final RestClient restClient;

    public ProductClient(RestClient.Builder builder, @Value("${product.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public ProductDto getProduct(UUID productId) {
        return restClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .body(ProductDto.class);
    }
}
