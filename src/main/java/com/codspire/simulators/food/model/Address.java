package com.codspire.simulators.food.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Address {
    private String streetAddress;
    private String city;
    private String stateAbbr;
    private String zip;
}
