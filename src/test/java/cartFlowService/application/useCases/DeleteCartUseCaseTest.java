package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeleteCartUseCaseTest {

    private final CartRepository cartRepository;
    private final DeleteCart deleteCart;

    DeleteCartUseCaseTest() {
        this.cartRepository = mock(CartRepository.class);
        this.deleteCart     = new DeleteCart(cartRepository);
    }


    @Test
    void shouldDeleteCartFromRepositoryById() throws CartNotFoundError {
        CartMaskId cartMaskId = new CartMaskId("6e55c340-9992-4d09-8986-8c19fc712f0b");
        when(cartRepository.findMakId(any(CartMaskId.class))).thenReturn(true);

        deleteCart.deleteCart(cartMaskId);

        verify(cartRepository).deleteCartByMaskId(any(CartMaskId.class));
    }

}
