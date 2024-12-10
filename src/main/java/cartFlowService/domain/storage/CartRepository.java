package cartFlowService.domain.storage;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;

import java.util.UUID;

public interface CartRepository {

    String createCart(Cart cart);

    boolean findMakId(CartId cartId);

    void deleteCartByMaskId(CartId cartId);

    Cart getCartById(UUID cartId);

    void updateItem(UUID cartId, Cart cart);

}
