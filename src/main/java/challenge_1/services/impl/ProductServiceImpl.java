package challenge_1.services.impl;

import challenge_1.domain.Product;
import challenge_1.domain.PurchaseReceipt;
import challenge_1.domain.User;
import challenge_1.entity.ProductEntity;
import challenge_1.exceptions.InsufficientFundsToBuyProductException;
import challenge_1.exceptions.NotAvailableAmountProductException;
import challenge_1.exceptions.ProductInvalidPriceDenominationException;
import challenge_1.exceptions.ProductNotFoundException;
import challenge_1.repository.ProductRepository;
import challenge_1.services.ChangeCalculator;
import challenge_1.services.ProductService;
import challenge_1.web.validators.MultipleOf5;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ChangeCalculator changeCalculator;
    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    public ProductServiceImpl(ChangeCalculator changeCalculator, ProductRepository productRepository, ConversionService conversionService) {
        this.changeCalculator = changeCalculator;
        this.productRepository = productRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void deleteById(String productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toProductEntity(product);
        entity = productRepository.save(entity);
        return toProduct(entity);
    }

    @Override
    public Page<Product> list(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::toProduct);
    }

    @Override
    @Transactional
    public PurchaseReceipt buy(User buyer, String productId, Integer amount) {
        return findOne(productId)
                .map(product -> {
                    if (amount > product.getAmountAvailable()) {
                        throw new NotAvailableAmountProductException();
                    }
                    int totalCost = amount * product.getCost();
                    //Take the closest possible amount of money from deposit
                    int deposit = buyer.takeFromDeposit(totalCost);
                    if (totalCost > deposit) {
                        throw new InsufficientFundsToBuyProductException();
                    }
                    deposit -= totalCost;
                    //Calculate change for return
                    int[] change = changeCalculator.calculate(MultipleOf5.DENOMINATIONS, deposit);
                    if (change[0] < 0) {
                        throw new ProductInvalidPriceDenominationException();
                    }
                    //Top up deposit with a return change
                    List<Integer> changeReturn = new ArrayList<>();
                    for (int i = 1; i < change.length; i++) {
                        if (change[i] > 0) {
                            int k = change[i];
                            while (k > 0) {
                                changeReturn.add(MultipleOf5.DENOMINATIONS[i - 1]);
                                k--;
                            }
                        }
                    }
                    changeReturn.forEach(buyer::topUpDeposit);
                    product.setAmountAvailable(product.getAmountAvailable() - amount);
                    save(product);
                    return new PurchaseReceipt()
                            .setProductId(productId)
                            .setTotal(totalCost)
                            .setChange(changeReturn);
                })
                .orElseThrow(ProductNotFoundException::new);

    }

    @Override
    public Optional<Product> findOne(String productId) {
        return productRepository.findById(productId)
                .map(this::toProduct);
    }

    private Product toProduct(ProductEntity source) {
        return conversionService.convert(source, Product.class);
    }

    private ProductEntity toProductEntity(Product product) {
        return conversionService.convert(product, ProductEntity.class);
    }
}