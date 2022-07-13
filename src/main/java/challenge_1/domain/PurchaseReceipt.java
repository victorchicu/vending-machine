package challenge_1.domain;


import java.util.List;

public class PurchaseReceipt {
    private String productId;
    private Integer total;
    private List<Integer> change;

    public String getProductId() {
        return productId;
    }

    public PurchaseReceipt setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PurchaseReceipt setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<Integer> getChange() {
        return change;
    }

    public PurchaseReceipt setChange(List<Integer> change) {
        this.change = change;
        return this;
    }
}
