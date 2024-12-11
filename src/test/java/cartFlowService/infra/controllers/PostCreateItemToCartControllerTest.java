package cartFlowService.infra.controllers;

import cartFlowService.application.request.CreateCartRequest;
import cartFlowService.application.useCases.CreateCart;
import cartFlowService.domain.errors.InvalidAmountError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class PostCreateItemToCartControllerTest {

    private final CreateCart createCart                                         = Mockito.mock(CreateCart.class);
    private final PostCreateItemToCartController postCreateItemToCartController = new PostCreateItemToCartController(createCart);

    @Test
    void canCreateCart() throws InvalidAmountError {
        CreateCartRequest request = new CreateCartRequest();
        request.setId(12);
        request.setDescription("party shoes");
        request.setAmount(25.99);

        ArrayList<CreateCartRequest> requestList = new ArrayList<>();
        requestList.add(request);

        postCreateItemToCartController.createCart(requestList);

        verify(createCart).createCart(any(ArrayList.class));
    }

    @Test
    void createCartThrowsInvalidAmountError() throws InvalidAmountError {
        CreateCartRequest invalidRequest = new CreateCartRequest();
        invalidRequest.setId(12);
        invalidRequest.setDescription("party shoes");
        invalidRequest.setAmount(0);

        ArrayList<CreateCartRequest> requestList = new ArrayList<>();
        requestList.add(invalidRequest);

        Assertions.assertThrows(InvalidAmountError.class, () -> {
            postCreateItemToCartController.createCart(requestList);
        });
    }
}
