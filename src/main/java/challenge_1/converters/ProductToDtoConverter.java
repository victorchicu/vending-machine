package challenge_1.converters;

import challenge_1.domain.Product;
import challenge_1.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToDtoConverter implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(Product source) {
        return new ProductDto(
                source.getId(),
                source.getSellerId(),
                source.getProductName(),
                source.getCost(),
                source.getAmountAvailable()
        );
    }
}
