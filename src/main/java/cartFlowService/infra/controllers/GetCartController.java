package cartFlowService.infra.controllers;

import cartFlowService.application.response.GetCartResponse;
import cartFlowService.application.useCases.GetCart;
import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.models.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class GetCartController {

    ObjectMapper objectMapper;
    GetCart      getCart;

    public GetCartController(ObjectMapper objectMapper, GetCart getCart) {
        this.objectMapper = objectMapper;
        this.getCart      = getCart;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCartResponse> getCart(@PathVariable UUID id) {
        Cart response = getCart.getCartById(id);

        GetCartResponse bodyResponse = new GetCartResponse(id.toString(), response.getItemList());

        return ResponseEntity.ok()
                .header("content-type", "application/json")
                .body(bodyResponse);
    }

    @ExceptionHandler(CartNotFoundError.class)
    public ResponseEntity<String> handleCartNotFoundError(CartNotFoundError ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }

}
