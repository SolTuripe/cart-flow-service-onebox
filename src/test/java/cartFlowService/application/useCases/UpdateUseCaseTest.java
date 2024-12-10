package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.errors.ItemNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.CartId;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    void shouldUpdateItemSuccessfully() {
        CartId cartId    = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        Item updatedItem = new Item(1, "party dress", 25.99);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "old item", 30.0));
        Cart mockCart            = new Cart(cartId, itemList);

        when(cartRepository.getCartById(cartId.value)).thenReturn(mockCart);

        updateCart.updateCart(cartId.value, updatedItem);

        verify(cartRepository).getCartById(cartId.value);
        verify(cartRepository).updateItem(cartId.value, mockCart);

        assert itemList.get(0).getDescription().equals("party dress");
        assert itemList.get(0).getAmount() == 25.99;
    }

    @Test
    void shouldFailIfCartIdNotExist() {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        Item item     = new Item(1, "party shoes", 30.0);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Cart cart = new Cart(cartId, itemList);

        when(cartRepository.findMakId(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundError.class, () -> updateCart.updateCart(cart.getId().value, item));
    }

    @Test
    void shouldThrowItemNotFoundError() {
        CartId cartId    = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        Item updatedItem = new Item(3, "updated Item", 15.0);

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "existing Item", 10.0));
        Cart mockCart            = new Cart(cartId, itemList);

        when(cartRepository.getCartById(cartId.value)).thenReturn(mockCart);

        assertThrows(ItemNotFoundError.class, () -> updateCart.updateCart(cartId.value, updatedItem));

        verify(cartRepository).getCartById(cartId.value);
        verify(cartRepository, never()).updateItem(any(), any());
    }
}
