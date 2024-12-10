package cartFlowService.infra.controllers;

import cartFlowService.application.useCases.DeleteCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.CartId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class DeleteCartControllerTest {

    private final DeleteCart deleteCart                     = Mockito.mock(DeleteCart.class);
    private final DeleteCartController deleteCartController = new DeleteCartController(deleteCart);

    @Test
    void shouldDeleteCart() throws CartNotFoundError {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");

        deleteCartController.deleteCartById(cartId.value);

        verify(deleteCart).deleteCart(cartId.value);
    }

    @Test
    public void shouldFailWhenGetCartFails() throws CartNotFoundError {
        CartId cartId = new CartId("6e55c340-9992-4d09-8986-8c19fc712f0b");

        doThrow(new CartNotFoundError(cartId.value.toString())).when(deleteCart).deleteCart(any());

        assertThrows(CartNotFoundError.class, () -> deleteCartController.deleteCartById(cartId.value));
    }
}
