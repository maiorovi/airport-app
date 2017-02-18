package util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repository.PassengerRepository;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = {"controller"})
public class TestWebappConfig {

	@Bean
	PassengerRepository passengerRepository() {
		return mock(PassengerRepository.class);
	}
}
