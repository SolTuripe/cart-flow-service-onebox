package cartFlowService.infra.repositories;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Repository implements CartRepository {

    private final ConcurrentHashMap<UUID, Cart> storage;

    public Repository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<UUID, Cart> getStorage() {
        return storage;
    }

    @Override
    public String createCart(Cart cart) {
        storage.put(cart.getId().value, cart);
        return cart.getId().value.toString();
    }

    @Override
    public boolean findMakId(CartMaskId cartMaskId) {
        return storage.containsKey(cartMaskId.value);
    }

    @Override
    public void deleteCartByMaskId(CartMaskId cartMaskId) {
        storage.remove(cartMaskId.value);
    }

    @Override
    public Cart getCartById(UUID cartId) {
        return storage.get(cartId);
    }

}
