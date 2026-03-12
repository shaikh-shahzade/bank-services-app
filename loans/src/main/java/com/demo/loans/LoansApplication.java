package com.demo.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.demo.loans.controller") })
@EnableJpaRepositories("com.demo.loans.repository")
@EntityScan("com.demo.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Shaikh Shahzade"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.demo.com"
				)
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}

