package co.simplon.product.config;

import co.simplon.product.entity.Product;
import co.simplon.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductInitializer {
    @Bean
    CommandLineRunner initializeProduct(ProductService productService) {
        return args-> {
            List<Product> products = List.of(
                    new Product(null, "MacBook Pro 14\"", "Informatique",
                            "Ordinateur portable haute performance avec puce M3 et écran Liquid Retina XDR"),
                    new Product(null, "Dell XPS 13", "Informatique",
                            "Ultrabook compact de 13 pouces, idéal pour la mobilité et le travail quotidien"),
                    new Product(null, "iPhone 15", "Téléphonie",
                            "Smartphone 6,1 pouces avec appareil photo 48 Mpx et connecteur USB-C"),
                    new Product(null, "Samsung Galaxy S24", "Téléphonie",
                            "Smartphone Android avec écran AMOLED 120 Hz et fonctions d'IA intégrées"),
                    new Product(null, "Sony WH-1000XM5", "Audio",
                            "Casque sans fil à réduction de bruit active, autonomie de 30 heures"),
                    new Product(null, "JBL Flip 6", "Audio",
                            "Enceinte Bluetooth portable et étanche, 12 heures d'autonomie"),
                    new Product(null, "Logitech MX Master 3S", "Accessoires",
                            "Souris ergonomique sans fil avec clics silencieux et molette électromagnétique"),
                    new Product(null, "Clavier mécanique Keychron K2", "Accessoires",
                            "Clavier mécanique sans fil compact 75 % compatible Mac et Windows"),
                    new Product(null, "Dyson V15 Detect", "Électroménager",
                            "Aspirateur balai sans fil avec détection laser de la poussière"),
                    new Product(null, "Nespresso Vertuo Next", "Électroménager",
                            "Machine à café à capsules avec extraction par centrifugation"));

            System.out.println("Products initialized:");
            products.forEach(product -> {
                Product saved = productService.create(product);
                System.out.println("ID: " + saved.getId() + ", Name: " + saved.getName()
                        + ", Category: " + saved.getCategory());
            });
        };
    }
}
