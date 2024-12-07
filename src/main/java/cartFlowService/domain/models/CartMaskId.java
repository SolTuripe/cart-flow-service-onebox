package cartFlowService.domain.models;

import java.util.Random;

public class CartMaskId {

    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int length        = 6;

    public final String value;

    public CartMaskId() {
        this.value = generateUniqueMask();
    }

    public CartMaskId(String value) {
        this.value = value;
    }

    /**
     * This method generates a unique maskId
     *
     * @return String
     */
    private String generateUniqueMask() {
        Random random = new Random();
        StringBuilder maskId = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            maskId.append(characters.charAt(index));
        }

        return maskId.toString();
    }

    public String getValue() {
        return value;
    }

}
