package cartFlowService.infra.controllers;

import cartFlowService.application.response.DeleteCartResponse;
import cartFlowService.application.useCases.DeleteCart;
import cartFlowService.domain.errors.CartNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class DeleteCartController {

    DeleteCart     deleteCart;

    public DeleteCartController(DeleteCart deleteCart) {
        this.deleteCart = deleteCart;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCartResponse> deleteCartById(@PathVariable UUID id) {
        deleteCart.deleteCart(id);

        DeleteCartResponse response = new DeleteCartResponse(
                200,
                "Cart with maskId = " + id + " was successfully deleted",
                "OK");

        return ResponseEntity.ok().header("content-type", "application/json")
                .body(response);
    }

    @ExceptionHandler(CartNotFoundError.class)
    public ResponseEntity<String> handleCartNotFoundError(CartNotFoundError ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }
}
