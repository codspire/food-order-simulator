package com.codspire.simulators.food;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class FakeOrderGeneratorTest {

	@Autowired
	private FakeOrderGenerator fakeOrderGenerator;

	@Test
	public void randomOrderItem() {
		assertThat(fakeOrderGenerator).isNotNull();

		assertThat(fakeOrderGenerator.get()).isNotNull();

		IntStream.range(0, 10).forEach(i -> System.out.println(fakeOrderGenerator.get()));
	}
}