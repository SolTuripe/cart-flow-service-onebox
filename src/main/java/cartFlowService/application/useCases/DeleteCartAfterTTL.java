package cartFlowService.application.useCases;

import cartFlowService.application.response.DeleteCartAfterTTLResponse;
import cartFlowService.domain.models.CartMaskId;
import cartFlowService.domain.storage.CartRepository;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DeleteCartAfterTTL {

    private final ScheduledExecutorService scheduler;
    private final Long                     ttl; // 10 minutes are 600 seconds

    public DeleteCartAfterTTL(Long ttl) {
        this.scheduler = new ScheduledThreadPoolExecutor(1);
        this.ttl       = ttl;
    }

    public void scheduleCartDeletion(CartMaskId cartMaskId, CartRepository cartRepository) {
        long start = System.currentTimeMillis();
        scheduler.schedule(() -> {
            long execStart = System.currentTimeMillis();
            cartRepository.deleteCartByMaskId(cartMaskId);

            // Use the instance of DeleteCartAfterTTLResponse
            DeleteCartAfterTTLResponse response = new DeleteCartAfterTTLResponse(
                    "Cart with id: " + cartMaskId.value + " deleted after: " + ttl + " min",
                    execStart - start
            );

            System.out.println(response);
        }, ttl, TimeUnit.MINUTES);
    }
}
