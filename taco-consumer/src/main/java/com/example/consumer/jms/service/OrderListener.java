package com.example.consumer.jms.service;

import com.example.consumer.test_ui.KitchenUI;
import com.example.producer.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class OrderListener {
    private final KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

//    @JmsListener(destination = "tacocloud.order.queue")
    public void proceedOrder(Order order) {
        ui.displayOrder(order);
    }
}
