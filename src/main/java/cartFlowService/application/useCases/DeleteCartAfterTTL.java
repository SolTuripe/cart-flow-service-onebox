package cartFlowService.application.useCases;

import cartFlowService.infra.DeleteCartTTL;

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

    public void scheduleCartDeletion(DeleteCartTTL deleteCartTTL) {
        scheduler.schedule(deleteCartTTL, ttl, TimeUnit.MILLISECONDS);
    }
}
