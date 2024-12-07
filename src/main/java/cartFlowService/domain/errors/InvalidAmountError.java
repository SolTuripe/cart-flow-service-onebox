package cartFlowService.domain.errors;

public class InvalidAmountError extends RuntimeException {
    public InvalidAmountError() {
        super("The amount cannot be less than or equal to zero");
    }
}
