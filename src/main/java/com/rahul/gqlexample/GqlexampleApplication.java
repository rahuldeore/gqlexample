package com.rahul.gqlexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class GqlexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GqlexampleApplication.class, args);
	}
}

@Controller
class CustomerGraphqlController {

	private final CustomerRepository customerRepository;

	public CustomerGraphqlController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	//@SchemaMapping (typeName = "Query", field = "customers")
	@QueryMapping()
	Flux<Customer> customers () {
		return this.customerRepository.findAll();
	}

}

@Repository
interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}

record Customer (@Id Integer id, String name) {

}


