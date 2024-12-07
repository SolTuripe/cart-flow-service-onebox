package cartFlowService.domain.models;

import java.util.UUID;

public class CartMaskId {

    public final UUID value;

    public CartMaskId() {
        this.value = UUID.randomUUID();
    }

    public CartMaskId(String value) {
        this.value = UUID.fromString(value);
    }

}
