package cartFlowService.infra;

import cartFlowService.application.response.DeleteCartAfterTTLResponse;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;

public class DeleteCartTTL implements Runnable {
    private final long       onset;
    private final CartMaskId cartMaskId;
    private long             execOnset;

    private final CartRepository cartRepository;

    public DeleteCartTTL(CartMaskId cartMaskId, CartRepository cartRepository) {
        this.onset          = System.currentTimeMillis();
        this.cartMaskId     = cartMaskId;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run() {
        execOnset = System.currentTimeMillis();
        cartRepository.deleteCartByMaskId(cartMaskId);

        // Use the instance of DeleteCartAfterTTLResponse
        DeleteCartAfterTTLResponse response = new DeleteCartAfterTTLResponse(
                "Cart with id: " + cartMaskId.value + " deleted after: " + (execOnset - onset) / 1000 + " sec",
                execOnset - onset
        );
        System.out.println(response);
    }

}
