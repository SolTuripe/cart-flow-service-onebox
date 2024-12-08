package cartFlowService.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cart {

    private CartMaskId      id;
    private ArrayList<Item> itemList;
    private LocalDateTime   createdAt;

    public Cart(CartMaskId id, ArrayList<Item> itemList) {
        this.id       = id;
        this.itemList = itemList;
    }

    public Cart(ArrayList<Item> itemList) {
        this.id       = new CartMaskId();
        this.itemList = itemList;
    }

    public CartMaskId getId() {
        return id;
    }

    public void setId(CartMaskId id) {
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
