package com.example.consumer.test_ui;

import com.example.producer.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(Order order) {
        log.info("RECEIVED ORDER:\n" + order.toString());
    }

    public void displayOrder(Order order, ConsumerRecord<Order, String> record) {
        log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp() + "   ----------------   Order: " + order);
    }
}
