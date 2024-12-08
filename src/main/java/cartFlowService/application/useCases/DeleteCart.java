package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;

import java.util.UUID;

public class DeleteCart {

    private final CartRepository cartRepository;

    public DeleteCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void deleteCart(UUID id) throws CartNotFoundError {
        CartMaskId cartMaskId = new CartMaskId(id.toString());

        if(!cartRepository.findMakId(cartMaskId)) throw new CartNotFoundError(cartMaskId.value.toString());
        cartRepository.deleteCartByMaskId(cartMaskId);
    }
}
