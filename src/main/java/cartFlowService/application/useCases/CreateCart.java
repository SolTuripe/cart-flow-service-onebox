package cartFlowService.application.useCases;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;

import java.util.ArrayList;

public class CreateCart {

    private final CartRepository cartRepository;
    private final DeleteCartAfterTTL deleteCartAfterTTL;

    public CreateCart(CartRepository cartRepository, DeleteCartAfterTTL deleteCartAfterTTL) {
        this.cartRepository     = cartRepository;
        this.deleteCartAfterTTL = deleteCartAfterTTL;
    }

    public String createCart(ArrayList<Item> productList) throws RuntimeException {
        // Create the cart with the items
        Cart cart = new Cart(productList);

        // Schedule cart deletion after TTL (10 min)
        deleteCartAfterTTL.scheduleCartDeletion(new CartMaskId(cartRepository.createCart(cart)), cartRepository);

        return cartRepository.createCart(cart);
    }

}
