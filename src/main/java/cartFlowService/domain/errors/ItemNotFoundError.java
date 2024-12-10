package cartFlowService.domain.errors;

public class ItemNotFoundError extends RuntimeException {
    public ItemNotFoundError(int itemId) {
        super("Item with id = " + itemId + " not found in the cart");
    }
}
