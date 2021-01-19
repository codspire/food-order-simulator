package com.codspire.simulators.food.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class OrderItem {
	private String name;
	private int quantity;
	private BigDecimal price;
}
