package com.codspire.simulators.food;

import com.codspire.simulators.food.model.PlacedOrder;
import com.codspire.simulators.food.utils.TrackTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FakePlacedOrderGenerator {
    @Autowired
    private FakeOrderGenerator fakeOrderGenerator;

    @Autowired
    private FakeCustomerGenerator fakeCustomerGenerator;

    @Autowired
    private FakeAddressGenerator fakeAddressGenerator;

    @TrackTime
    public PlacedOrder get() {
        PlacedOrder order = PlacedOrder.builder()
                .order(fakeOrderGenerator.get())
                .deliveryAddress(fakeAddressGenerator.get())
                .customer(fakeCustomerGenerator.get())
                .build();

        log.debug("Returning OrderId: {}", order.getOrder().getOrderId());

        return order;
    }
}