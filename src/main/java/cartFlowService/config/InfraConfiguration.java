package cartFlowService.config;


import cartFlowService.application.useCases.CreateCart;
import cartFlowService.application.useCases.DeleteCart;
import cartFlowService.application.useCases.DeleteCartAfterTTL;
import cartFlowService.domain.storage.CartRepository;
import cartFlowService.infra.repositories.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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


}
