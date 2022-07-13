package challenge_1.exceptions;

public class ProductInvalidPriceDenominationException extends ProductException {
    public ProductInvalidPriceDenominationException() {
        super("Product price not valid due to incorrect denomination");
    }
}
