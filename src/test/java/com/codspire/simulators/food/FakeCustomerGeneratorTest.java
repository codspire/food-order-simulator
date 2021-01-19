package com.codspire.simulators.food;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class FakeCustomerGeneratorTest {

	@Autowired
	private FakeCustomerGenerator fakeCustomerGenerator;

	@Test
	public void getFakeCustomer() {

		assertThat(fakeCustomerGenerator.get()).isNotNull();

		assertThat(fakeCustomerGenerator.get().getId()).isNotEmpty();
		assertThat(fakeCustomerGenerator.get().getFirstName()).isNotEmpty();
		assertThat(fakeCustomerGenerator.get().getLastName()).isNotEmpty();
		assertThat(fakeCustomerGenerator.get().getEmail()).isNotEmpty();
		assertThat(fakeCustomerGenerator.get().getPhone()).isNotEmpty();
	}
}
