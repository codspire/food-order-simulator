package com.codspire.simulators.food;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class FakeAddressGeneratorTest {

	@Autowired
	private FakeAddressGenerator fakeAddressGenerator;

	@Test
	public void faker() {

		Faker faker = new Faker(new Locale("en-US"), new RandomService());

		for (int i = 0; i < 10; i++) {
			System.out.println(faker.name().fullName());
			System.out.println(faker.funnyName().name());
			System.out.println(faker.address().fullAddress());
			System.out.println(faker.address().streetAddress(true));
			System.out.println(faker.address().city());
			System.out.println(faker.address().stateAbbr());
			System.out.println(faker.address().zipCodeByState("VA"));
			System.out.println(faker.food().dish());
			System.out.println(faker.bothify("????##@gmail.com"));
			System.out.println(faker.random().nextInt(5, 10));
			System.out.println();
		}

	}

	@Test
	public void generateFakeAddress() {
		for (int i = 0; i < 10; i++) {
			System.out.println(fakeAddressGenerator.get());
		}
	}

	@Test
	public void getFakeAddress() {
		assertThat(fakeAddressGenerator.get()).isNotNull();
		assertThat(fakeAddressGenerator.get().getZip()).startsWith("201");
		assertThat(fakeAddressGenerator.get().getStreetAddress()).isNotEmpty();
		assertThat(fakeAddressGenerator.get().getStateAbbr()).isEqualTo("VA");
		assertThat(fakeAddressGenerator.get().getCity()).isNotEmpty();
	}
}
