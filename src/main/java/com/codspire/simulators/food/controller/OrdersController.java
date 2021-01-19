package com.codspire.simulators.food.controller;

import com.codspire.simulators.food.FakePlacedOrderGenerator;
import com.codspire.simulators.food.model.PlacedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class OrdersController {

	@Autowired
	private FakePlacedOrderGenerator fakePlacedOrderGenerator;

	@GetMapping(path = "/orders/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<PlacedOrder> orderStream() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(sequence -> fakePlacedOrderGenerator.get());
	}
}
