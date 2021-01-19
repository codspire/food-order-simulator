package com.codspire.simulators.food.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Customer {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
//	private Address address;
}
