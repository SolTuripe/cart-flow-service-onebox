package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;

import java.util.UUID;

public class GetCart {

    private final CartRepository cartRepository;

    public GetCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartById(UUID id) {
        CartMaskId cartMaskId = new CartMaskId(id.toString());

        if (!cartRepository.findMakId(cartMaskId)) throw new CartNotFoundError(cartMaskId.value.toString());

        return cartRepository.getCartById(cartMaskId.value);
    }

}
