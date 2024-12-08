package cartFlowService.infra.controllers;

import cartFlowService.application.response.DeleteCartResponse;
import cartFlowService.application.useCases.DeleteCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class DeleteCartController {

    DeleteCart     deleteCart;
    CartRepository cartRepository;

    public DeleteCartController(DeleteCart deleteCart, CartRepository cartRepository) {
        this.deleteCart = deleteCart;
        this.cartRepository = cartRepository;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCartResponse> deleteCartByMaskId(@PathVariable UUID id) {
        CartMaskId cartMaskId = new CartMaskId(id.toString());

        if(!cartRepository.findMakId(cartMaskId)) throw new CartNotFoundError(cartMaskId.value.toString());

        deleteCart.deleteCart(cartMaskId);

        DeleteCartResponse response = new DeleteCartResponse(
                200,
                "Cart with maskId = " + id + " was successfully deleted",
                "OK");

        return ResponseEntity.ok().header("content-type", "application/json")
                .body(response);
    }

    /**
     * Handles CartNotFoundError exceptions thrown during application execution
     *
     * @param ex The `CartNotFoundError` exception containing the error message
     * @return A `ResponseEntity` object with status code 404 and a JSON message describing the error
     */
    @ExceptionHandler(CartNotFoundError.class)
    public ResponseEntity<String> handleCartNotFoundError(CartNotFoundError ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }
}
