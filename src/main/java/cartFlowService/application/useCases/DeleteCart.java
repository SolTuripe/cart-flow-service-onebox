package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.storage.CartRepository;

import java.util.UUID;

public class DeleteCart {

    private final CartRepository cartRepository;

    public DeleteCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void deleteCart(UUID id) throws CartNotFoundError {
        CartId cartId = new CartId(id.toString());
        if (!cartRepository.findMakId(cartId)) throw new CartNotFoundError(cartId.value.toString());

        cartRepository.deleteCartByMaskId(cartId);
    }
}
