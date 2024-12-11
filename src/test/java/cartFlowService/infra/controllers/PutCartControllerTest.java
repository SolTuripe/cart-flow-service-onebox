package cartFlowService.infra.controllers;

import cartFlowService.application.request.CreateCartRequest;
import cartFlowService.application.useCases.GetCart;
import cartFlowService.application.useCases.UpdateCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class PutCartControllerTest {

    private final UpdateCart updateCart                   = Mockito.mock(UpdateCart.class);
    private final GetCart getCart                         = Mockito.mock(GetCart.class);
    private final PutCartController putCartController     = new PutCartController(updateCart, getCart);

    @Test
    void shouldUpdateCartSuccessfully() throws CartNotFoundError {
        CartId cartId      = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        int itemId         = 1;
        String description = "Updated Item";
        double amount      = 15.0;

        Item updatedItem         = new Item(itemId, description, amount);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(updatedItem);

        ArrayList<CreateCartRequest> requestList = new ArrayList<>();
        for (Item item : itemList) {
            CreateCartRequest request = new CreateCartRequest();
            request.setDescription(item.getDescription());
            request.setAmount(item.getAmount());
            requestList.add(request);
        }

        ArrayList<Item> initialItems = new ArrayList<>();
        initialItems.add(new Item(1, "Old Item", 30.0));
        Cart mockCart = new Cart(cartId, initialItems);

        when(getCart.getCartById(cartId.value)).thenReturn(mockCart);
        doNothing().when(updateCart).updateCart(eq(cartId.value), eq(itemList));

        putCartController.updateCart(cartId.value, requestList);

        verify(updateCart).updateCart(eq(cartId.value), argThat(items -> {
            Item item = items.get(0);
            return item.getDescription().equals(updatedItem.getDescription()) &&
                    item.getAmount() == updatedItem.getAmount();
        }));

        verify(getCart).getCartById(cartId.value);

    }

    @Test
    public void shouldFailWhenCartNotFound() throws CartNotFoundError  {
        CartId cartId      = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        String description = "Updated Item";
        double amount      = 15.0;

        CreateCartRequest request                = new CreateCartRequest();
        request.setDescription(description);
        request.setAmount(amount);
        ArrayList<CreateCartRequest> requestList = new ArrayList<>();
        requestList.add(request);

        doThrow(new CartNotFoundError(cartId.toString())).when(updateCart).updateCart(any(), any());

        assertThrows(CartNotFoundError.class, () -> putCartController.updateCart(cartId.value, requestList));
    }

}
