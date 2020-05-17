package com.example.consumer.kafka;

import com.example.consumer.test_ui.KitchenUI;
import com.example.producer.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

//@Component
public class OrderListener {
    private final KitchenUI ui;

//    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

//    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "tacocloud_kitchen")
    public void processOrder(Order order, ConsumerRecord<Order, String> record) {
        ui.displayOrder(order, record);
    }
}
