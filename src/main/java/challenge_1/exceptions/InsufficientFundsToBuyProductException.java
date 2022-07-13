package challenge_1.exceptions;

public class InsufficientFundsToBuyProductException extends ProductException {
    public InsufficientFundsToBuyProductException() {
        super("Insufficient funds to buy a product");
    }
}