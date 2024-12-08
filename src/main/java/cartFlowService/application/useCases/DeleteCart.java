package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;

public class DeleteCart {

    private final CartRepository cartRepository;

    public DeleteCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void deleteCart(CartMaskId cartMaskId) throws CartNotFoundError {
        cartRepository.deleteCartByMaskId(cartMaskId);
    }
}
