package cartFlowService.infra.controllers;

import cartFlowService.application.useCases.GetCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetCartControllerTest {

    private final GetCart getCart                     = Mockito.mock(GetCart.class);
    private final GetCartController getCartController = new GetCartController(getCart);

    @Test
    void shouldReturnCartSuccessfully() {
        CartId cartId            = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        ArrayList<Item> itemList = new ArrayList<>();

        itemList.add(new Item(1, "party dress", 25.99));
        Cart mockCart = new Cart(cartId, itemList);

        when(getCart.getCartById(cartId.value)).thenReturn(mockCart);

        getCartController.getCart(cartId.value);

        verify(getCart).getCartById(cartId.value);
    }

    @Test
    void shouldFailWhenGetCartFails() throws CartNotFoundError {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");

        when(getCart.getCartById(cartId.value)).thenThrow(new CartNotFoundError(cartId.value.toString()));

        assertThrows(CartNotFoundError.class, () -> getCart.getCartById(cartId.value));
    }

}
