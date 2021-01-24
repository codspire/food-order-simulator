package com.codspire.simulators.food.controller;

import com.codspire.simulators.food.model.PlacedOrder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrdersControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(OrdersControllerTest.class);

	@LocalServerPort
	private int port;

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testOrdersStream() {

		List<PlacedOrder> placedOrders = webClient
				.get().uri("/orders/stream")
				.accept(MediaType.valueOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
				.exchange()
				.expectStatus().isOk()
				.returnResult(PlacedOrder.class)
				.getResponseBody()
				.take(5) // take 5 objects
				.collectList()
				.block(Duration.ofSeconds(20));

		placedOrders.forEach(System.out::println);

		assertThat(placedOrders.size()).isEqualTo(5);
	}


	@Test
	public void testOrdersStreamSSE() throws InterruptedException {
//		WebClient client = WebClient.create("http://localhost:" + port);
//		ParameterizedTypeReference<ServerSentEvent<PlacedOrder>> type
//				= new ParameterizedTypeReference<ServerSentEvent<PlacedOrder>>() {
//		};
//
//		Flux<ServerSentEvent<PlacedOrder>> eventStream = client.get()
//				.uri("/orders/stream-sse")
//				.retrieve()
//				.bodyToFlux(type);
//
//		eventStream.subscribe(
//				content -> LOG.info("Time: {} - event: name[{}], id [{}], content[{}] ", LocalTime.now(), content.event(), content.id(), content.data()),
//				error -> LOG.error("Error receiving SSE: {}", error), () -> LOG.info("Completed!!!"));


		List<PlacedOrder> placedOrders = webClient
				.get().uri("/orders/stream-sse")
				.accept(MediaType.valueOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
				.exchange()
				.expectStatus().isOk()
				.returnResult(PlacedOrder.class)
				.getResponseBody()
				.take(5) // take 5 objects
				.collectList()
				.block(Duration.ofSeconds(20));

		placedOrders.forEach(System.out::println);

		assertThat(placedOrders.size()).isEqualTo(5);

		Thread.sleep(10000);
	}
}