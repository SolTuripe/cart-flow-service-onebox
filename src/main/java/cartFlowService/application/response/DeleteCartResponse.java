package cartFlowService.application.response;

public class DeleteCartResponse {

    private int    code;
    private String message;
    private String status;

    public DeleteCartResponse(int code, String message, String status) {
        this.code       = code;
        this.message    = message;
        this.status     = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

}
