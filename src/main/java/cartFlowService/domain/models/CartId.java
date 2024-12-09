package cartFlowService.domain.models;

import java.util.UUID;

public class CartId {

    public final UUID value;

    public CartId() {
        this.value = UUID.randomUUID();
    }

    public CartId(String value) {
        this.value = UUID.fromString(value);
    }

}
