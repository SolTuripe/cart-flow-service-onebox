package cartFlowService.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cart {

    private CartId          id;
    private ArrayList<Item> itemList;
    private LocalDateTime   createdAt;

    public Cart(CartId id, ArrayList<Item> itemList) {
        this.id       = id;
        this.itemList = itemList;
    }

    public Cart(ArrayList<Item> itemList) {
        this.id       = new CartId();
        this.itemList = itemList;
    }

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdAt = createdTime;
    }
}
