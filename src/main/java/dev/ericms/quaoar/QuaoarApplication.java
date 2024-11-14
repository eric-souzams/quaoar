package dev.ericms.quaoar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
public class QuaoarApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuaoarApplication.class, args);
	}

}
