package com.codspire.simulators.food;

import com.codspire.simulators.food.model.PlacedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FakePlacedOrderGenerator {
	@Autowired
	private FakeOrderGenerator fakeOrderGenerator;

	@Autowired
	private FakeCustomerGenerator fakeCustomerGenerator;

	@Autowired
	private FakeAddressGenerator fakeAddressGenerator;


	public PlacedOrder get() {
		return PlacedOrder.builder()
				.order(fakeOrderGenerator.get())
				.deliveryAddress(fakeAddressGenerator.get())
				.customer(fakeCustomerGenerator.get())
				.build();
	}
}