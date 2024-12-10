package cartFlowService.application.useCases;

import cartFlowService.domain.errors.CartNotFoundError;
import cartFlowService.domain.errors.ItemNotFoundError;
import cartFlowService.domain.models.Cart;
import cartFlowService.domain.models.Item;
import cartFlowService.domain.storage.CartRepository;

import java.util.ArrayList;
import java.util.UUID;

public class UpdateCart {
    private final CartRepository cartRepository;

    public UpdateCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void updateCart(UUID carId, Item updatedItem) {

        Cart cart = cartRepository.getCartById(carId);
        if (cart == null) {
            throw new CartNotFoundError(carId.toString());
        }

        ArrayList<Item> itemList = cart.getItemList();

        boolean itemExists = itemList.stream().anyMatch(item -> item.getId() == updatedItem.getId());
        if (!itemExists) {
            throw new ItemNotFoundError(updatedItem.getId());
        }

        itemList.replaceAll(existingItem ->
                existingItem.getId() == updatedItem.getId() ? updatedItem : existingItem);

        cartRepository.updateItem(carId, cart);
    }
}
