package cartFlowService.infra.controllers;

import cartFlowService.application.request.CreateCartRequest;
import cartFlowService.application.response.GetCartResponse;
import cartFlowService.application.useCases.GetCart;
import cartFlowService.application.useCases.UpdateCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.errors.InvalidAmountError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class PutCartController {

    UpdateCart updateCart;
    GetCart    getCart;

    public PutCartController(UpdateCart updateCart, GetCart getCart) {
        this.updateCart = updateCart;
        this.getCart    = getCart;
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCartResponse> updateCart(@PathVariable UUID id, @RequestBody ArrayList<CreateCartRequest> requestList) {

        ArrayList<Item> updatedCart = requestList.stream()
                .map(this::mapRequestToItem)
                .collect(Collectors.toCollection(ArrayList::new));

        updateCart.updateCart(id, updatedCart);

        Cart cartWithNewItems             = getCart.getCartById(id);
        GetCartResponse bodyResponse      = new GetCartResponse(id.toString(), cartWithNewItems.getItemList());

        return ResponseEntity.ok().header("content-type", "application/json")
                .body(bodyResponse);
    }

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

    @ExceptionHandler(CartNotFoundError.class)
    public ResponseEntity<String> handleCartNotFoundError(CartNotFoundError ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }
}
