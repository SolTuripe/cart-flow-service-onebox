package cartFlowService.config;


import cartFlowService.application.useCases.CreateCart;
import cartFlowService.domain.storage.CartRepository;
import cartFlowService.infra.repositories.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraConfiguration {

    @Bean
    CartRepository cartRepository() {
        return new Repository();
    }

    @Bean
    CreateCart createCart(CartRepository cartRepository) {
        return new CreateCart(cartRepository);
    }

}
