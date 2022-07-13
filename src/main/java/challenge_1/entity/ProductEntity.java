package challenge_1.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductEntity {
    @Id
    private String id;
    @Version
    private Long version;
    @CreatedBy
    private String sellerId;
    private String productName;
    private Integer cost;
    private Integer amountAvailable;

    public String getId() {
        return id;
    }

    public ProductEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getSellerId() {
        return sellerId;
    }

    public ProductEntity setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ProductEntity setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public ProductEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public ProductEntity setCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public static class Field {
        public static final String ID = "_id";
        public static final String AMOUNT_AVAILABLE = "amountAvailable";
    }
}