package com.example.producer.jms.service;

import com.example.producer.service.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import com.example.producer.model.Order;

@Service
public class JMSOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;

    @Autowired
    public JMSOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend("tacocloud.order.queue", order, webOrderSourceProc);
    }

    private final MessagePostProcessor webOrderSourceProc = message -> {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    };
}
