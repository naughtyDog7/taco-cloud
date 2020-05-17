package com.example.consumer.rabbit_mq;

import com.example.consumer.test_ui.KitchenUI;
import com.example.producer.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final KitchenUI kitchenUI;

    @Autowired
    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @RabbitListener(queues = "tacocloud.order.queue")
    public void processOrder(Order order) {
        kitchenUI.displayOrder(order);
    }
}
