package co.simplon.product;

import co.simplon.product.client.InventoryClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ProductApplicationTests {

    @MockitoBean
    InventoryClient InventoryClientient;

    @Test
    void contextLoads() {
    }

}
