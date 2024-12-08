package cartFlowService.infra.controllers;

import cartFlowService.application.request.CreateCartRequest;
import cartFlowService.application.response.CreateCartResponse;
import cartFlowService.domain.errors.InvalidAmountError;
import cartFlowService.domain.models.Item;
import cartFlowService.application.useCases.CreateCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class PostCreateItemToCartController {

    CreateCart createCart;

    public PostCreateItemToCartController(CreateCart createCart) {
        this.createCart = createCart;
    }

    @PostMapping("/")
    public ResponseEntity<CreateCartResponse> createCart(@RequestBody ArrayList<CreateCartRequest> requestList) {
        ArrayList<Item> itemList = requestList.stream()
                // Convert each CreateCartRequest into an Item
                .map(this::mapRequestToItem)
                .collect(Collectors.toCollection(ArrayList::new));

        // Create the cart with the list of Items
        String cartId = createCart.createCart(itemList);

        CreateCartResponse response = new CreateCartResponse(
                200,
                cartId,
                "Cart created successfully",
                "OK");

        return ResponseEntity.ok().header("content-type", "application/json")
                .body(response);
    }

    /**
     * Converts a CreateCartRequest object into an Item object
     * Validates that the provided amount is greater than zero
     *
     * @param request the CreateCartRequest containing item details to be mapped
     * @return a new Item object constructed from the request data
     * @throws InvalidAmountError if the amount in the request is less than or equal to zero
     */
    private Item mapRequestToItem(CreateCartRequest request) {
        if (request.getAmount() <= 0) {
            throw new InvalidAmountError();
        }

        return new Item(
                request.getId(),
                request.getDescription(),
                request.getAmount()
        );
    }

    /**
     * Handles InvalidAmountError exceptions thrown during application execution
     *
     * @param ex The `InvalidAmountError` exception containing the error message
     * @return A `ResponseEntity` object with status code 400 and a JSON message describing the error
     */
    @ExceptionHandler(InvalidAmountError.class)
    public ResponseEntity<String> handleInvalidAmountError(InvalidAmountError ex) {
        return ResponseEntity
                .badRequest()
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }

}
