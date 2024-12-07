package cartFlowService.domain.storage;

import cartFlowService.domain.models.Cart;

public interface CartRepository {

    String createCart(Cart cart);

}
