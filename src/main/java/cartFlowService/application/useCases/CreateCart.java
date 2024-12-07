package cartFlowService.application.useCases;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;

import java.util.ArrayList;

public class CreateCart {
    private final CartRepository cartRepository;

    public CreateCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public String createCart(ArrayList<Item> productList) throws RuntimeException{
        Cart cart = new Cart(productList);

        return cartRepository.createCart(cart);
    }

}
