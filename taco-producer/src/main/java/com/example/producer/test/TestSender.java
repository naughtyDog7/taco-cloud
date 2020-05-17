package com.example.producer.test;

import com.example.producer.model.Order;
import com.example.producer.service.OrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class TestSender {
    private final OrderMessagingService sender;

    public TestSender(@Qualifier("rabbitOrderMessagingService") OrderMessagingService sender) {
        this.sender = sender;
    }

    @Scheduled(fixedDelay = 10000L, initialDelay = 10000L)
    public void testSendMessage() {
        Order order = new Order();
        order.setId(0);
        sender.sendOrder(order);
        log.info("Sent order " + order);
    }
}
