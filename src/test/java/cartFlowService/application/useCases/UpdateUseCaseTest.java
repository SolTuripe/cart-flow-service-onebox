package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UpdateUseCaseTest {

    private final CartRepository cartRepository;
    private final UpdateCart     updateCart;

    UpdateUseCaseTest() {
        this.cartRepository = mock(CartRepository.class);
        this.updateCart     = new UpdateCart(cartRepository);
    }

    @Test
    void shouldUpdateItemSuccessfully() throws CartNotFoundError {
        CartId cartId    = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        Item updatedItem = new Item(1, "party dress", 25.99);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "old item", 30.0));
        Cart cart                = new Cart(cartId, itemList);

        when(cartRepository.getCartById(cartId.value)).thenReturn(cart);
        when(cartRepository.findMakId(any(CartId.class))).thenReturn(true);

        updateCart.updateCart(cart.getId().value, new ArrayList<>(List.of(updatedItem)));

        verify(cartRepository).addItemsToACart(any(Cart.class));

    }

    @Test
    void shouldFailIfCartIdNotExist() {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        Item item     = new Item(1, "party shoes", 30.0);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Cart cart = new Cart(cartId, itemList);

        when(cartRepository.findMakId(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundError.class, () -> updateCart.updateCart(cart.getId().value, itemList));
    }

}
