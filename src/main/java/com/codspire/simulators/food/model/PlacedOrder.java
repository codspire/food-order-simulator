package com.codspire.simulators.food.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PlacedOrder {
	private Customer customer;

	private Address deliveryAddress;

	private Order order;
}
