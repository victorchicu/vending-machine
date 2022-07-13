package challenge_1.repository.converters;

import challenge_1.domain.Product;
import challenge_1.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToProductConverter implements Converter<ProductEntity, Product> {
    @Override
    public Product convert(ProductEntity source) {
        return new Product()
                .setId(source.getId())
                .setSellerId(source.getSellerId())
                .setProductName(source.getProductName())
                .setCost(source.getCost())
                .setAmountAvailable(source.getAmountAvailable());
    }
}
