package com.codspire.simulators.food;

import com.codspire.simulators.food.model.Address;
import com.codspire.simulators.food.utils.TrackTime;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Component
public class FakeAddressGenerator {
	private Faker faker;

	@Value("${address.state}")
	private String stateAbbr;

	@Value("${address.zipCodePattern}")
	private String zipCodePattern;

	@Value("${address.locale}")
	private String locale;

	@PostConstruct
	public void init() {
		this.faker = new Faker(new Locale(locale), new RandomService());
	}

	@TrackTime
	public Address get() {
		com.github.javafaker.Address fakeAddress = faker.address();
		return Address.builder()
				.streetAddress(fakeAddress.streetAddress(true))
				.city(fakeAddress.city())
				.zip(isNotEmpty(zipCodePattern) ? faker.bothify(zipCodePattern)
						: isNotEmpty(stateAbbr) ? fakeAddress.zipCodeByState(stateAbbr) : fakeAddress.zipCode())
				.stateAbbr(isNotEmpty(stateAbbr) ? stateAbbr : fakeAddress.stateAbbr())
				.build();
	}
}