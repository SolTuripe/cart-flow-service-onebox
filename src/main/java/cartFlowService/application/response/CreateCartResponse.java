package cartFlowService.application.response;

public class CreateCartResponse {

    private int    code;
    private String cartMaskId;
    private String message;
    private String status;

    public CreateCartResponse(int code, String cartMaskId, String message, String status) {
        this.code       = code;
        this.cartMaskId = cartMaskId;
        this.message    = message;
        this.status     = status;
    }

    public int getCode() {
        return code;
    }

    public String getCartMaskId() {
        return cartMaskId;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
