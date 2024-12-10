package cartFlowService.infra.controllers;

import cartFlowService.application.request.PatchCartRequest;
import cartFlowService.application.response.GetCartResponse;
import cartFlowService.application.useCases.GetCart;
import cartFlowService.application.useCases.UpdateCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.errors.ItemNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class PatchCartController {

    UpdateCart updateCart;
    GetCart    getCart;

    public PatchCartController(UpdateCart updateCart, GetCart getCart) {
        this.updateCart = updateCart;
        this.getCart    = getCart;
    }

    @PatchMapping("/{id}/items/{itemId}")
    public ResponseEntity<GetCartResponse> updateCart(@PathVariable UUID id, @PathVariable int itemId, @RequestBody PatchCartRequest request) {

        Item updatedItem = new Item(
                itemId,
                request.getDescription(),
                request.getAmount()
        );

        updateCart.updateCart(id, updatedItem);

        Cart updatedCart             = getCart.getCartById(id);
        GetCartResponse bodyResponse = new GetCartResponse(id.toString(), updatedCart.getItemList());

        return ResponseEntity.ok().header("content-type", "application/json")
                .body(bodyResponse);
    }

    @ExceptionHandler({CartNotFoundError.class, ItemNotFoundError.class})
    public ResponseEntity<String> handleNotFoundErrors(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }
}
