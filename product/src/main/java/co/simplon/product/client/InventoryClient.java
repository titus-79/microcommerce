package co.simplon.product.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class InventoryClient {
    private final RestClient restClient;

    public InventoryClient(RestClient.Builder builder, @Value("${inventory.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public void initializeStock(UUID productId) {
        restClient.post()
                .uri("/inventory/{productId}", productId)
                .retrieve()
                .toBodilessEntity();
    }

}
