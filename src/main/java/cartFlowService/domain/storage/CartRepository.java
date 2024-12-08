package cartFlowService.domain.storage;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartMaskId;

public interface CartRepository {

    String createCart(Cart cart);

    boolean findMakId(CartMaskId cartMaskId);

    void deleteCartByMaskId(CartMaskId cartMaskId);

}
