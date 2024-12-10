package cartFlowService.config;

import cartFlowService.application.useCases.*;
import cartFlowService.domain.storage.CartRepository;
import cartFlowService.infra.repositories.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class InfraConfiguration {

    @Value("${ttl}")
    private Long ttl;

    @Bean
    CartRepository cartRepository() {
        return new Repository();
    }

    @Bean
    CreateCart createCart(CartRepository cartRepository, DeleteCartAfterTTL deleteCartAfterTTL) {
        return new CreateCart(cartRepository, deleteCartAfterTTL);
    }

    @Bean
    DeleteCart deleteCart(CartRepository cartRepository){
        return new DeleteCart(cartRepository);
    }

    @Bean
    DeleteCartAfterTTL deleteCartAfterTTL() {
        return new DeleteCartAfterTTL(ttl);
    }

    @Bean
    GetCart getCart(CartRepository cartRepository) {
        return new GetCart(cartRepository);
    }

    @Bean
    UpdateCart updateCart(CartRepository cartRepository) {
        return new UpdateCart(cartRepository);
    }


}
