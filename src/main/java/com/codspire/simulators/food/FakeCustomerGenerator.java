package com.codspire.simulators.food;

import com.codspire.simulators.food.model.Customer;
import com.github.javafaker.Faker;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FakeCustomerGenerator {
	private final Faker faker = new Faker();

	private ULID ulid = new ULID();

	@Value("${customer.emailPattern}")
	private String emailPattern;

	public Customer get() {

		return Customer.builder()
				.id(ulid.nextULID())
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.phone(faker.phoneNumber().cellPhone())
				.email(faker.bothify(emailPattern))
				.build();
	}
}