package challenge_1.exceptions;

public class NotAvailableAmountProductException extends ProductException {
    public NotAvailableAmountProductException() {
        super("No product amount available");
    }
}
