package com.codspire.simulators.food;

import com.codspire.simulators.food.model.OrderItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class RandomOrderItem {
	private List<OrderItem> orderItems;
	private int itemsCount;
	private Random random = new Random();

	@Value("${order.maxItemQuantity}")
	private int randomMaxQuantity;


	public OrderItem get() {
		OrderItem randomOrderItem = orderItems.get(random.ints(1, itemsCount - 1).findAny().getAsInt());
		randomOrderItem.setQuantity(random.ints(1, randomMaxQuantity).findAny().getAsInt());
		return randomOrderItem;
	}

	@PostConstruct
	public void init() {

		File file = new File("src/main/resources/restaurant-2-products-price.csv");

		try (InputStream inputStream = new FileInputStream(file)) {
			BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));

			orderItems = bufferedReader.lines().skip(1).map(line -> {
				return OrderItem.builder()
						.name(StringUtils.trim(line.split(",")[0]))
						.price(new BigDecimal(StringUtils.trim(line.split(",")[1])))
						.build();
			}).collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}

		itemsCount = orderItems.size();
	}
}