package cartFlowService.application.response;

import cartFlowService.domain.models.Item;

import java.util.ArrayList;

public class GetCartResponse {

    private String          cartId;
    private ArrayList<Item> items;

    public GetCartResponse(String cartId, ArrayList<Item> items) {
        this.cartId = cartId;
        this.items  = items;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
