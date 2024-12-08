package cartFlowService.domain.storage;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartMaskId;

import java.util.UUID;

public interface CartRepository {

    String createCart(Cart cart);

    boolean findMakId(CartMaskId cartMaskId);

    void deleteCartByMaskId(CartMaskId cartMaskId);

    Cart getCartById(UUID cartId);

}
