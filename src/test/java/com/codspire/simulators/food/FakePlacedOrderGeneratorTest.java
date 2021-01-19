package com.codspire.simulators.food;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class FakePlacedOrderGeneratorTest {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	Gson gson1 = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
//			ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime())
//			.setPrettyPrinting()
//			.create();
//
//	Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
//		@Override
//		public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//			Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
//			return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//		}
//	}).setPrettyPrinting().create();

	@Autowired
	private FakePlacedOrderGenerator fakePlacedOrderGenerator;

	@Test
	public void randomOrderItem() {
		assertThat(fakePlacedOrderGenerator).isNotNull();

		assertThat(fakePlacedOrderGenerator.get()).isNotNull();
		assertThat(fakePlacedOrderGenerator.get().getOrder()).isNotNull();
		assertThat(fakePlacedOrderGenerator.get().getCustomer()).isNotNull();
		assertThat(fakePlacedOrderGenerator.get().getDeliveryAddress()).isNotNull();

		IntStream.range(0, 10).forEach(i -> System.out.println(gson.toJson(fakePlacedOrderGenerator.get())));
	}
}