package challenge_1.converters;

import challenge_1.domain.Product;
import challenge_1.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToProductConverter implements Converter<ProductDto, Product> {
    @Override
    public Product convert(ProductDto source) {
        return new Product()
                .setId(source.getId())
                .setProductName(source.getProductName())
                .setCost(source.getCost())
                .setAmountAvailable(source.getAmountAvailable());
    }
}