package cartFlowService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"cartFlowService.config",
				"cartFlowService.infra.controllers"
		}
)
public class ECommerceCartFlowServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ECommerceCartFlowServiceApplication.class, args);
	}

}
