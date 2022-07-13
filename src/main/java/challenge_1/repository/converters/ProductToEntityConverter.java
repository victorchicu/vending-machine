package challenge_1.repository.converters;

import challenge_1.domain.Product;
import challenge_1.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToEntityConverter implements Converter<Product, ProductEntity> {
    @Override
    public ProductEntity convert(Product source) {
        ProductEntity entity = new ProductEntity();

        return entity
                .setId(source.getId())
                .setSellerId(source.getSellerId())
                .setProductName(source.getProductName())
                .setCost(source.getCost())
                .setAmountAvailable(source.getAmountAvailable());
    }
}