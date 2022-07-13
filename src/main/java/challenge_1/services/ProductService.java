package challenge_1.services;

import challenge_1.domain.Product;
import challenge_1.domain.PurchaseReceipt;
import challenge_1.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    void deleteById(String productId);

    Product save(Product product);

    Page<Product> list(Pageable pageable);

    PurchaseReceipt buy(User buyer, String productId, Integer amount);

    Optional<Product> findOne(String productId);
}