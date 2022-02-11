package com.webfluxexample;

import com.webfluxexample.entity.Category;
import com.webfluxexample.entity.Customer;
import com.webfluxexample.entity.Food;
import com.webfluxexample.repository.CustomerRepository;
import com.webfluxexample.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class WebfluxExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner initFoodData(FoodRepository repository) {
		return args -> {
			repository.count()
				.filter(count -> count == 0)
				.hasElement()
				.flatMapMany(doesntHaveItems -> {
					if (doesntHaveItems) {
						return Flux.just(
							Food.of("Pizza", Category.FAST_FOOD),
							Food.of("Hamburger", Category.FAST_FOOD),
							Food.of("Salad dish", Category.FITNESS),
							Food.of("Cole slaw", Category.FITNESS),
							Food.of("Barbacue", Category.NORMAL),
							Food.of("Rice and beans", Category.NORMAL)
						)
						.flatMap(repository::save)
						.doOnComplete(() -> System.out.println("Initialize data foods with success!"));
					}

					return Flux.empty();
				})
				.subscribe();
		};
	}

	@Bean
	CommandLineRunner initCustomerData(CustomerRepository repository) {
		return args -> {
			repository.count()
				.filter(count -> count == 0)
				.hasElement()
				.flatMapMany(doesntHaveItems -> {
					if (doesntHaveItems) {
						return Flux.just(
							Customer.of("John Snow"),
							Customer.of("Mrs Heisenberg"),
							Customer.of("Rocky Balboa")
						)
						.flatMap(repository::save)
						.doOnComplete(() -> System.out.println("Initialize data customers with success!"));
					}

					return Flux.empty();
				})
				.subscribe();
		};
	}
}
