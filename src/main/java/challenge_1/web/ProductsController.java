package challenge_1.web;

import challenge_1.domain.Product;
import challenge_1.dto.ProductDto;
import challenge_1.exceptions.UserNotFoundException;
import challenge_1.services.ProductService;
import challenge_1.services.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Validated
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final UserService userService;
    private final ProductService productService;
    private final ConversionService conversionService;

    public ProductsController(UserService userService, ProductService productService, ConversionService conversionService) {
        this.userService = userService;
        this.productService = productService;
        this.conversionService = conversionService;
    }

    @PreAuthorize("hasAnyRole('SELLER')")
    @PostMapping
    public ProductDto createProduct(Principal principal, @RequestBody @Valid ProductDto productDto) {
        return userService.findById(principal.getName())
                .map(user -> {
                    Product product = toProduct(productDto);
                    product = productService.save(product);
                    return toProductDto(product);
                })
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    public Page<ProductDto> listProducts(@PageableDefault(page = 0, size = 100) Pageable pageable) {
        return productService.list(pageable)
                .map(this::toProductDto);
    }

    private Product toProduct(ProductDto source) {
        return conversionService.convert(source, Product.class);
    }

    private ProductDto toProductDto(Product source) {
        return conversionService.convert(source, ProductDto.class);
    }
}
