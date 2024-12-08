package cartFlowService.application.response;

public class DeleteCartAfterTTLResponse {

    private  String message;
    private  long   ttl;

    public DeleteCartAfterTTLResponse(String message, long ttl) {
        this.message = message;
        this.ttl     = ttl;
    }

    public String getMessage() {
        return message;
    }

    public long getTtl() {
        return ttl;
    }

    @Override
    public String toString() {
        return "Delete Cart After TTL Response: " +
                "{" + "message = '" + message + '\'' +
                ", ttl in milliseconds = " + ttl +
                '}';
    }
}
