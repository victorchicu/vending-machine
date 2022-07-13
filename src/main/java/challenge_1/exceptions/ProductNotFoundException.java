package challenge_1.exceptions;

public class ProductNotFoundException extends ProductException {
    public ProductNotFoundException() {
        super("Product not found");
    }
}
