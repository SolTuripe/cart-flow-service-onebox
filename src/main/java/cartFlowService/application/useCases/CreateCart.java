package cartFlowService.application.useCases;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;
import cartFlowService.infra.DeleteCartTTL;

import java.util.ArrayList;

public class CreateCart {

    private final CartRepository     cartRepository;
    private final DeleteCartAfterTTL deleteCartAfterTTL;

    public CreateCart(CartRepository cartRepository, DeleteCartAfterTTL deleteCartAfterTTL) {
        this.cartRepository     = cartRepository;
        this.deleteCartAfterTTL = deleteCartAfterTTL;
    }

    public String createCart(ArrayList<Item> itemList) throws RuntimeException {
        Cart cart = new Cart(itemList);

        // Schedule cart deletion after TTL 10 min (600 sec)
        deleteCartAfterTTL.scheduleCartDeletion(new DeleteCartTTL(cart.getId(), cartRepository));

        return cartRepository.createCart(cart);
    }

}
