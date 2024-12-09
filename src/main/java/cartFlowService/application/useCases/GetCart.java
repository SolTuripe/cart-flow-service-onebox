package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.storage.CartRepository;

import java.util.UUID;

public class GetCart {

    private final CartRepository cartRepository;

    public GetCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartById(UUID id) {
        CartId cartId = new CartId(id.toString());

        if (!cartRepository.findMakId(cartId)) throw new CartNotFoundError(cartId.value.toString());

        return cartRepository.getCartById(cartId.value);
    }

}
