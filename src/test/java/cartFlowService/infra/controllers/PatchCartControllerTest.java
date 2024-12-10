package cartFlowService.infra.controllers;

import cartFlowService.application.request.PatchCartRequest;
import cartFlowService.application.useCases.GetCart;
import cartFlowService.application.useCases.UpdateCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.errors.ItemNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class PatchCartControllerTest {

    private final UpdateCart updateCart                   = Mockito.mock(UpdateCart.class);
    private final GetCart getCart                         = Mockito.mock(GetCart.class);
    private final PatchCartController patchCartController = new PatchCartController(updateCart, getCart);

    @Test
    void shouldUpdateCartSuccessfully() {
        CartId cartId      = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        int itemId         = 1;
        String description = "Updated Item";
        double amount      = 15.0;

        Item updatedItem         = new Item(itemId, description, amount);
        ArrayList<Item> itemList = new ArrayList<>();

        itemList.add(new Item(itemId, "Old Item", 30.0));
        Cart mockCart = new Cart(cartId, itemList);

        doNothing().when(updateCart).updateCart(cartId.value, updatedItem);
        when(getCart.getCartById(cartId.value)).thenReturn(mockCart);

        PatchCartRequest request = new PatchCartRequest();
        request.setDescription(description);
        request.setAmount(amount);

        patchCartController.updateCart(cartId.value, itemId, request);

        verify(updateCart).updateCart(eq(cartId.value), argThat(item ->
                item.getId() == updatedItem.getId() &&
                        item.getDescription().equals(updatedItem.getDescription()) &&
                        item.getAmount() == updatedItem.getAmount()
        ));
        verify(getCart).getCartById(cartId.value);
    }

    @Test
    public void shouldFailWhenCartNotFound() {
        CartId cartId      = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        int itemId         = 1;
        String description = "Updated Item";
        double amount      = 15.0;

        PatchCartRequest request = new PatchCartRequest();
        request.setDescription(description);
        request.setAmount(amount);

        doThrow(new CartNotFoundError(cartId.toString())).when(updateCart).updateCart(any(), any());

        assertThrows(CartNotFoundError.class, () -> patchCartController.updateCart(cartId.value, itemId, request));
    }

    @Test
    public void shouldFailWhenItemNotFound() {
        CartId cartId      = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        int itemId         = 1;
        String description = "Updated Item";
        double amount      = 15.0;

        PatchCartRequest request = new PatchCartRequest();
        request.setDescription(description);
        request.setAmount(amount);

        Cart mockCart = new Cart(cartId, new ArrayList<>());

        when(getCart.getCartById(cartId.value)).thenReturn(mockCart);
        doThrow(new ItemNotFoundError(itemId)).when(updateCart).updateCart(any(), any());

        assertThrows(ItemNotFoundError.class, () -> patchCartController.updateCart(cartId.value, itemId, request));
    }

}
