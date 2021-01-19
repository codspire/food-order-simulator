package com.codspire.simulators.food;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulatorsApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SimulatorsApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");

		SpringApplication.run(SimulatorsApplication.class, args);

		LOG.info("APPLICATION FINISHED");
	}
}