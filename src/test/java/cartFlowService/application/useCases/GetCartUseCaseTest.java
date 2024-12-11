package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetCartUseCaseTest {

    private final CartRepository cartRepository;
    private final GetCart        getCart;

    GetCartUseCaseTest() {
        this.cartRepository = mock(CartRepository.class);
        this.getCart        = new GetCart(cartRepository);
    }

    @Test
    void shouldGetCartById() throws CartNotFoundError {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        Item item      = new Item(12, "party shoes", 25.99);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Cart newCart = new Cart(cartId, itemList);

        when(cartRepository.findMakId(any(CartId.class))).thenReturn(true);
        getCart.getCartById(cartId.value);

        assertEquals(cartId, newCart.getId());
    }

    @Test
    void shouldFailIfCartDoesNotExist() throws CartNotFoundError {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");

        when(cartRepository.findMakId(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundError.class, () -> getCart.getCartById(cartId.value));
    }

}
