package cartFlowService.infra.repositories;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
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
    public boolean findMakId(CartId cartId) {
        return storage.containsKey(cartId.value);
    }

    @Override
    public void deleteCartByMaskId(CartId cartId) {
        storage.remove(cartId.value);
    }

    @Override
    public Cart getCartById(UUID cartId) {
        return storage.get(cartId);
    }

    @Override
    public void addItemsToACart(Cart cart) {
        storage.put(cart.getId().value, cart);
    }

}
