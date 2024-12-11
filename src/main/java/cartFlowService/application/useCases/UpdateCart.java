package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;

import java.util.ArrayList;
import java.util.UUID;

public class UpdateCart {
    private final CartRepository cartRepository;

    public UpdateCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void updateCart(UUID carId, ArrayList<Item> updatedItem) {

        CartId cartId     = new CartId(carId.toString());
        Cart existingCart = cartRepository.getCartById(cartId.value);

        if (existingCart == null) {
            throw new CartNotFoundError(cartId.value.toString());
        }
        existingCart.getItemList().addAll(updatedItem);

        cartRepository.addItemsToACart(existingCart);
    }
}
