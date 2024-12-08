package cartFlowService.application.useCases;

import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateCartUseCaseTest {

    private final CartRepository     cartRepository;
    private final DeleteCartAfterTTL deleteCartAfterTTL;
    private final CreateCart         createCart;

    CreateCartUseCaseTest() {
        this.cartRepository     = mock(CartRepository.class);
        this.deleteCartAfterTTL = mock(DeleteCartAfterTTL.class);
        this.createCart         = new CreateCart(cartRepository, deleteCartAfterTTL);
    }

    @Test
    void shouldCreateCart() {
        Item item                = new Item(12, "party shoes", 25.99);
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item);

        createCart.createCart(itemList);

        verify(cartRepository).createCart(any(Cart.class));
    }
}
