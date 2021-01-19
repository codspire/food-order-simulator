package com.codspire.simulators.food;

import com.codspire.simulators.food.model.Order;
import com.codspire.simulators.food.model.OrderItem;
import com.github.javafaker.Faker;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FakeOrderGenerator {
	private final Faker faker = new Faker();
	private ULID ulid = new ULID();
	private Random random = new Random();

	@Value("${order.maxOrderItemQuantity}")
	private int maxOrderItemQuantity;

	@Value("${order.taxPercentage}")
	private BigDecimal taxPercentage;

	@Autowired
	private RandomOrderItem randomOrderItem;

	public Order get() {

		Order order = Order.builder()
				.orderId(ulid.nextULID())
				.confirmationCode(UUID.randomUUID().toString().substring(0, 8))
				.orderTime(LocalDateTime.now())
				.build();

		return updateOrderItems(order);
	}

	private Order updateOrderItems(Order order) {
		List<OrderItem> orderItems = randomOrderItems();
		BigDecimal total = orderItems.stream()
				.map(i -> i.getPrice().multiply(new BigDecimal(i.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal tax = total.multiply(taxPercentage).setScale(2, RoundingMode.CEILING);

		order.setOrderItems(orderItems);
		order.setTaxAmount(tax);
		order.setDiscountAmount(BigDecimal.ZERO.setScale(2, RoundingMode.CEILING));

		order.setAmount(total.add(tax).setScale(2, RoundingMode.CEILING));
		order.setItemsCount(orderItems.size());
		return order;
	}

	private List<OrderItem> randomOrderItems() {
		return IntStream.range(0, random.ints(1, maxOrderItemQuantity).findAny().getAsInt())
				.mapToObj(i -> randomOrderItem.get()).collect(Collectors.toList());
	}
}