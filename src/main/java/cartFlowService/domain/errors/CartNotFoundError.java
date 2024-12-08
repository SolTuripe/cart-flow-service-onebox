package cartFlowService.domain.errors;

public class CartNotFoundError extends RuntimeException {
    public CartNotFoundError(String id) {
        super("Cart id = " + id + " not found");
    }
}
