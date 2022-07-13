package challenge_1.domain;


public class Product {
    private String id;
    private String sellerId;
    private String productName;
    private Integer cost;
    private Integer amountAvailable;

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getSellerId() {
        return sellerId;
    }

    public Product setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public Product setCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public Product setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
        return this;
    }
}
