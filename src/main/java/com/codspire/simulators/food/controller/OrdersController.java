package com.codspire.simulators.food.controller;

import com.codspire.simulators.food.FakePlacedOrderGenerator;
import com.codspire.simulators.food.model.PlacedOrder;
import com.codspire.simulators.food.utils.TrackTime;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class OrdersController {

	@Autowired
	private FakePlacedOrderGenerator fakePlacedOrderGenerator;

	@GetMapping(path = "/orders/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
	@TrackTime
	public Flux<PlacedOrder> orderStream() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(sequence -> fakePlacedOrderGenerator.get());
	}

	@GetMapping(path = "/orders/stream-sse", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<ServerSentEvent<PlacedOrder>> orderStreamSSE() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(sequence -> ServerSentEvent.<PlacedOrder>builder()
						.id(String.valueOf(sequence))
//						.event("periodic-event")
						.data(fakePlacedOrderGenerator.get())
						.build());
	}

	@GetMapping(path = "/orders/stream-sse2", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Publisher<PlacedOrder> httpReactiveSource() {
//		return Flux.<PlacedOrder>generate(sink -> sink.next(fakePlacedOrderGenerator.get())).take(10);
		return Flux
				.<PlacedOrder>generate(sink -> sink.next(fakePlacedOrderGenerator.get()))
				.delayElements(Duration.ofSeconds(1));
	}
}
