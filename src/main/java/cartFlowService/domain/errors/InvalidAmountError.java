package cartFlowService.domain.errors;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("The amount cannot be less than or equal to zero");
    }
}
