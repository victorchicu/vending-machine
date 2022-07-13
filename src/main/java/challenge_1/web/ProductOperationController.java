package challenge_1.web;

import challenge_1.domain.PurchaseReceipt;
import challenge_1.dto.PurchaseReceiptDto;
import challenge_1.exceptions.UserNotFoundException;
import challenge_1.services.ProductService;
import challenge_1.services.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/products/{productId}")
public class ProductOperationController {
    private final UserService userService;
    private final ProductService productService;
    private final ConversionService conversionService;

    public ProductOperationController(UserService userService, ProductService productService, ConversionService conversionService) {
        this.userService = userService;
        this.productService = productService;
        this.conversionService = conversionService;
    }

    @PreAuthorize("hasAnyRole('SELLER')")
    @DeleteMapping
    public void deleteProduct(
            Principal principal,
            @PathVariable String productId
    ) {
        userService.findById(principal.getName())
                .ifPresentOrElse(
                        seller -> productService.deleteById(productId),
                        UserNotFoundException::new
                );
    }

    @PreAuthorize("hasAnyRole('BUYER')")
    @PutMapping("/buy")
    public PurchaseReceiptDto buyProduct(
            Principal principal,
            @PathVariable String productId,
            @Valid
            @NotNull(message = "Amount is null or not valid")
            @Min(value = 1, message = "Amount must be at least 1")
            @Max(value = 100, message = "Amount must not exceed 100")
            @RequestParam Integer amount
    ) {
        return userService.findById(principal.getName())
                .map(buyer -> {
                    PurchaseReceipt purchaseReceipt = productService.buy(buyer, productId, amount);
                    userService.save(buyer);
                    return toPurchaseReceiptDto(purchaseReceipt);
                })
                .orElseThrow(UserNotFoundException::new);
    }

    private PurchaseReceiptDto toPurchaseReceiptDto(PurchaseReceipt source) {
        return conversionService.convert(source, PurchaseReceiptDto.class);
    }
}
