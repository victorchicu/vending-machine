package challenge_1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class PurchaseReceiptDto {
    private final String productId;
    private final Integer total;
    private final List<Integer> change;

    @JsonCreator
    public PurchaseReceiptDto(String productId, Integer total, List<Integer> change) {
        this.productId = productId;
        this.total = total;
        this.change = change;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getTotal() {
        return total;
    }

    public List<Integer> getChange() {
        return change;
    }
}
