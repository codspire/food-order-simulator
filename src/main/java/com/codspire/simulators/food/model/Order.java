package com.codspire.simulators.food.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
public class Order {
//	private String customerId;
	private String orderId;
	private String confirmationCode;

	private LocalDateTime orderTime;

	private List<OrderItem> orderItems;
	private int itemsCount;

	private BigDecimal discountAmount;
	private BigDecimal taxAmount;
	private BigDecimal amount;
}
