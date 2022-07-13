package challenge_1.dto;

import challenge_1.web.validators.MultipleOf5;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class ProductDto {
    private final String id;
    private final String sellerId;
    private final String productName;
    @MultipleOf5(message = "Cost must be multiple of 5")
    private final Integer cost;
    @Min(value = 1, message = "Amount must be at least 5")
    @Max(value = 100, message = "Amount must not exceed 100")
    private final Integer amountAvailable;

    @JsonCreator
    public ProductDto(String id, String sellerId, String productName, Integer cost, Integer amountAvailable) {
        this.id = id;
        this.sellerId = sellerId;
        this.productName = productName;
        this.amountAvailable = amountAvailable;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }
}
