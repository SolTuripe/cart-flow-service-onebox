package cartFlowService.infra;

import cartFlowService.application.response.DeleteCartAfterTTLResponse;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.storage.CartRepository;

public class DeleteCartTTL implements Runnable {
    private final long       onset;
    private final CartId     cartId;
    private long             execOnset;

    private final CartRepository cartRepository;

    public DeleteCartTTL(CartId cartId, CartRepository cartRepository) {
        this.onset          = System.currentTimeMillis();
        this.cartId         = cartId;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run() {
        execOnset = System.currentTimeMillis();
        cartRepository.deleteCartByMaskId(cartId);

        // Use the instance of DeleteCartAfterTTLResponse
        DeleteCartAfterTTLResponse response = new DeleteCartAfterTTLResponse(
                "Cart with id: " + cartId.value + " deleted after: " + (execOnset - onset) / 1000 + " sec",
                execOnset - onset
        );
        System.out.println(response);
    }

}
